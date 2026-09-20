# Semana 7 - Implementación del Patrón Prototype

## Proyecto

**AgroPrecision - Sistema de Agricultura de Precisión**

**Asignatura:** Patrones de Diseño de Software  
**Patrón implementado:** Prototype  
**Categoría GoF:** Creacional  

---

## 1. Descripción del patrón

Prototype es un patrón de diseño creacional perteneciente al catálogo GoF.

Su objetivo es crear nuevos objetos a partir de la copia de una instancia existente denominada prototipo.

En lugar de reconstruir un objeto desde cero, el sistema puede reutilizar una configuración existente y generar una nueva instancia independiente.

En AgroPrecision, Prototype fue implementado dentro del microservicio `irrigation-service` para reutilizar configuraciones de planes de riego previamente definidos.

---

## 2. Justificación del uso

Prototype es conveniente en AgroPrecision porque diferentes parcelas o cultivos pueden compartir parámetros de riego similares.

Un plan de riego puede contener:

- Umbral de humedad del suelo.
- Duración del riego.
- Volumen de agua.
- Modo automático.
- Prioridad.

Si ya existe una configuración validada, no es necesario reconstruir todos esos parámetros nuevamente.

Prototype permite utilizar un `IrrigationPlan` existente como plantilla y generar una copia independiente para otro cultivo o zona.

Ejemplo:

```text
Prototipo

Cultivo: CROP-01
Zona: A1
Humedad: 35 %
Duración: 25 minutos
Agua: 180 litros
Automático: true
Prioridad: HIGH

        |
        | copyFor()
        v

Clon

Cultivo: CROP-02
Zona: B1
Humedad: 35 %
Duración: 25 minutos
Agua: 180 litros
Automático: true
Prioridad: HIGH
```

---

## 3. Relación con Builder

Builder y Prototype trabajan sobre `IrrigationPlan`, pero resuelven problemas diferentes.

```text
Builder
→ construye un IrrigationPlan desde cero paso a paso.

Prototype
→ genera un nuevo IrrigationPlan copiando uno previamente configurado.
```

Por esta razón ambos patrones pueden coexistir dentro de `irrigation-service`.

---

## 4. Interfaz Prototype

Se implementó la interfaz genérica:

`Prototype<T>`

Ubicación:

`services/irrigation-service/src/main/java/com/agroprecision/irrigation/prototype/Prototype.java`

Código:

```java
public interface Prototype<T> {

    T copy();
}
```

La interfaz establece que cualquier objeto que actúe como Prototype debe ser capaz de generar una copia de sí mismo.

---

## 5. Concrete Prototype

La clase:

`IrrigationPlan`

implementa:

```java
Prototype<IrrigationPlan>
```

De esta manera `IrrigationPlan` actúa como Concrete Prototype.

Ejemplo:

```java
public class IrrigationPlan
        implements Prototype<IrrigationPlan> {
```

---

## 6. Método copy()

El método:

```java
@Override
public IrrigationPlan copy() {
    return new IrrigationPlan(
            cropId,
            zone,
            soilMoistureThreshold,
            durationMinutes,
            waterVolumeLiters,
            automatic,
            priority
    );
}
```

crea un nuevo objeto `IrrigationPlan` utilizando todos los valores del objeto original.

El resultado mantiene la misma configuración, pero corresponde a una nueva instancia en memoria.

---

## 7. Método copyFor()

Además se implementó:

```java
public IrrigationPlan copyFor(
        String newCropId,
        String newZone
) {
    return new IrrigationPlan(
            newCropId,
            newZone,
            soilMoistureThreshold,
            durationMinutes,
            waterVolumeLiters,
            automatic,
            priority
    );
}
```

Este método permite reutilizar la configuración del prototipo y modificar únicamente:

- El cultivo.
- La zona.

Los demás parámetros permanecen iguales.

Esto permite reutilizar planes de riego en parcelas con condiciones similares.

---

## 8. Servicio de Prototype

Se implementó:

`IrrigationPlanPrototypeService`

Ubicación:

`services/irrigation-service/src/main/java/com/agroprecision/irrigation/application/prototype/IrrigationPlanPrototypeService.java`

El servicio mantiene un plan prototipo:

```java
this.prototype =
        director.createAutomaticPlan(
                "CROP-TEMPLATE",
                "TEMPLATE"
        );
```

Posteriormente puede generar una copia mediante:

```java
return prototype.copyFor(
        cropId,
        zone
);
```

---

## 9. Integración mediante API REST

Prototype fue integrado en `IrrigationController`.

Se agregó el endpoint:

```text
GET /api/irrigation/prototype-plan
```

Ejemplo:

```text
http://localhost:8082/api/irrigation/prototype-plan?cropId=CROP-02&zone=B1
```

Respuesta esperada:

```json
{
  "cropId": "CROP-02",
  "zone": "B1",
  "soilMoistureThreshold": 35.0,
  "durationMinutes": 25,
  "waterVolumeLiters": 180.0,
  "automatic": true,
  "priority": "HIGH"
}
```

El cultivo y la zona cambian, mientras que la configuración principal del prototipo se conserva.

---

## 10. Flujo de funcionamiento

```text
IrrigationController
        |
        v
IrrigationPlanPrototypeService
        |
        v
IrrigationPlan
   (Prototype)
        |
        | copyFor()
        v
Nuevo IrrigationPlan
```

---

## 11. Pruebas automatizadas

Se implementó:

`IrrigationPlanPrototypeTest`

La primera prueba verifica que `copy()` genere un objeto diferente al original.

```java
IrrigationPlan copy =
        original.copy();

assertNotSame(
        original,
        copy
);
```

`assertNotSame` confirma que original y copia son objetos independientes en memoria.

También se verifica que sus valores sean iguales.

---

## 12. Prueba de reutilización

La segunda prueba utiliza:

```java
IrrigationPlan clone =
        prototype.copyFor(
                "CROP-02",
                "B1"
        );
```

Se comprueba que:

```text
cropId → CROP-02
zone   → B1
```

mientras se conservan:

```text
soilMoistureThreshold
durationMinutes
waterVolumeLiters
automatic
priority
```

---

## 13. Resultado de las pruebas

Las pruebas fueron ejecutadas con Maven:

```text
.\mvnw.cmd test
```

Resultado:

```text
Tests run: 4
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

Dentro de `IrrigationPlanPrototypeTest` se ejecutaron dos pruebas específicas del patrón Prototype.

---

## 14. Beneficios para AgroPrecision

Prototype aporta:

- Reutilización de configuraciones existentes.
- Reducción de construcción repetitiva.
- Creación rápida de planes similares.
- Independencia entre el prototipo y sus copias.
- Menor duplicación de lógica.
- Facilidad para trabajar con parcelas de características similares.

---

## 15. Evidencia en video

La evidencia se encuentra en:

`Semana 7 (Prototype)/video/Prototype_AgroPrecision_Semana7.mov`

---

## 16. Diagrama UML

El UML correspondiente se encuentra en:

`Semana 7 (Prototype)/uml/Prototype_AgroPrecision.png`

---

## 17. Conclusión

Prototype fue implementado exitosamente dentro del microservicio `irrigation-service`.

`IrrigationPlan` actúa como Concrete Prototype y permite generar copias independientes mediante `copy()`.

Además, `copyFor()` permite reutilizar una configuración de riego existente para otro cultivo o zona.

La implementación fue integrada mediante una API REST y validada mediante pruebas automatizadas, obteniendo `BUILD SUCCESS`.

Prototype complementa al patrón Builder: Builder permite construir un plan desde cero y Prototype permite reutilizar un plan ya construido.