# Semana 5 - Implementación del Patrón Builder

## Proyecto

**AgroPrecision - Sistema de Agricultura de Precisión**

**Asignatura:** Patrones de Diseño de Software  
**Patrón implementado:** Builder  
**Categoría GoF:** Creacional  

---

## 1. Descripción del patrón

Builder es un patrón de diseño creacional perteneciente a los patrones GoF.

Su objetivo es permitir la construcción de objetos complejos paso a paso, separando el proceso de construcción de la representación final del objeto.

Este patrón es conveniente en AgroPrecision porque un plan de riego necesita múltiples parámetros de configuración, como cultivo, zona, nivel de humedad, duración, volumen de agua, modo automático y prioridad.

En lugar de utilizar un constructor con muchos parámetros, Builder permite construir el plan de manera progresiva, legible y extensible.

---

## 2. Problema identificado

El sistema AgroPrecision debe generar planes de riego para diferentes cultivos y zonas.

Un plan de riego puede contener información como:

- Identificador del cultivo.
- Zona de riego.
- Umbral de humedad del suelo.
- Duración del riego.
- Volumen de agua.
- Modo automático.
- Prioridad.

Crear directamente este objeto mediante un constructor tradicional produciría una instrucción extensa y difícil de interpretar.

Ejemplo:

```java
new IrrigationPlan(
    "CROP-01",
    "A1",
    35.0,
    25,
    180.0,
    true,
    "HIGH"
);
```

Builder permite reemplazar esta construcción por un proceso más legible.

---

## 3. Microservicio utilizado

Para implementar Builder se creó el microservicio:

`irrigation-service`

Ubicación:

`services/irrigation-service`

El servicio utiliza:

- Java 21.
- Spring Boot.
- Maven.
- Spring Web.

El microservicio se ejecuta en:

```text
http://localhost:8082
```

---

## 4. Product - IrrigationPlan

La clase:

`IrrigationPlan`

representa el producto que será construido.

Ubicación:

`services/irrigation-service/src/main/java/com/agroprecision/irrigation/domain/model/IrrigationPlan.java`

El objeto contiene:

```text
cropId
zone
soilMoistureThreshold
durationMinutes
waterVolumeLiters
automatic
priority
```

Esto representa la configuración completa de un plan de riego.

---

## 5. Builder

La interfaz:

`IrrigationPlanBuilder`

define las operaciones necesarias para construir el plan.

Ubicación:

`services/irrigation-service/src/main/java/com/agroprecision/irrigation/application/builder/IrrigationPlanBuilder.java`

Ejemplo:

```java
IrrigationPlanBuilder cropId(String cropId);

IrrigationPlanBuilder zone(String zone);

IrrigationPlanBuilder soilMoistureThreshold(
        double soilMoistureThreshold
);

IrrigationPlanBuilder durationMinutes(
        int durationMinutes
);

IrrigationPlanBuilder waterVolumeLiters(
        double waterVolumeLiters
);

IrrigationPlanBuilder automatic(
        boolean automatic
);

IrrigationPlanBuilder priority(
        String priority
);

IrrigationPlan build();
```

Cada método devuelve un `IrrigationPlanBuilder`, permitiendo encadenar las operaciones.

---

## 6. Concrete Builder

La clase:

`AutomaticIrrigationPlanBuilder`

implementa `IrrigationPlanBuilder`.

Esta clase almacena progresivamente los valores necesarios para construir el plan.

Ejemplo:

```java
IrrigationPlan plan = builder
        .cropId("CROP-01")
        .zone("A1")
        .soilMoistureThreshold(35.0)
        .durationMinutes(25)
        .waterVolumeLiters(180.0)
        .automatic(true)
        .priority("HIGH")
        .build();
```

El método:

```java
build()
```

finaliza el proceso y genera el objeto `IrrigationPlan`.

---

## 7. Director

La clase:

`IrrigationPlanDirector`

coordina el proceso de construcción.

Ubicación:

`services/irrigation-service/src/main/java/com/agroprecision/irrigation/application/director/IrrigationPlanDirector.java`

El Director utiliza un Builder para definir una configuración completa de riego automático.

Ejemplo:

```java
return builder
        .cropId(cropId)
        .zone(zone)
        .soilMoistureThreshold(35.0)
        .durationMinutes(25)
        .waterVolumeLiters(180.0)
        .automatic(true)
        .priority("HIGH")
        .build();
```

De esta manera, el Director conoce el proceso de construcción, mientras que el Builder se encarga de crear el producto.

---

## 8. Integración mediante API REST

La implementación se integra mediante:

`IrrigationController`

El controlador recibe el cultivo y la zona y solicita al Director la creación del plan.

Endpoint:

```text
GET /api/irrigation/automatic-plan
```

Ejemplo:

```text
http://localhost:8082/api/irrigation/automatic-plan?cropId=CROP-01&zone=A1
```

Respuesta:

```json
{
  "cropId": "CROP-01",
  "zone": "A1",
  "soilMoistureThreshold": 35.0,
  "durationMinutes": 25,
  "waterVolumeLiters": 180.0,
  "automatic": true,
  "priority": "HIGH"
}
```

---

## 9. Flujo de funcionamiento

```text
IrrigationController
        |
        v
IrrigationPlanDirector
        |
        v
IrrigationPlanBuilder
        |
        v
AutomaticIrrigationPlanBuilder
        |
        v
build()
        |
        v
IrrigationPlan
```

---

## 10. Prueba automatizada

Se implementó:

`AutomaticIrrigationPlanBuilderTest`

Ubicación:

`services/irrigation-service/src/test/java/com/agroprecision/irrigation/application/builder/AutomaticIrrigationPlanBuilderTest.java`

La prueba construye un plan mediante Builder:

```java
IrrigationPlan plan = builder
        .cropId("CROP-01")
        .zone("A1")
        .soilMoistureThreshold(35.0)
        .durationMinutes(25)
        .waterVolumeLiters(180.0)
        .automatic(true)
        .priority("HIGH")
        .build();
```

Posteriormente verifica que todos los valores hayan sido construidos correctamente.

El resultado obtenido mediante Maven fue:

```text
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

---

## 11. Beneficios para AgroPrecision

Builder aporta los siguientes beneficios:

- Facilita la construcción de objetos con múltiples parámetros.
- Mejora la legibilidad del código.
- Evita constructores extensos y difíciles de interpretar.
- Permite configurar el objeto paso a paso.
- Separa la construcción del producto final.
- Facilita agregar nuevos parámetros a los planes de riego.
- Permite crear distintas configuraciones de riego utilizando el mismo proceso de construcción.

---

## 12. Justificación de uso

Builder es apropiado para AgroPrecision porque `IrrigationPlan` es un objeto compuesto por múltiples datos relacionados con la configuración del riego.

El patrón permite representar claramente cómo se construye un plan de riego sin acoplar el código cliente a un constructor con numerosos argumentos.

Por esta razón, su utilización dentro de `irrigation-service` tiene una aplicación real dentro del dominio del sistema y no corresponde únicamente a una implementación académica aislada.

---

## 13. Arquitectura actual

```text
AgroPrecision
|
+-- identity-service
|   └── Singleton
|
+-- sensor-service
|   └── Factory Method
|
└── irrigation-service
    └── Builder
```

Cada patrón se encuentra asociado a una responsabilidad concreta del sistema.

---

## 14. Evidencia en video

La demostración del patrón Builder se encuentra en:

`Semana 5 (Builder)/video/Builder_AgroPrecision_Semana5.mov`

El video presenta:

- `IrrigationPlan`.
- `IrrigationPlanBuilder`.
- `AutomaticIrrigationPlanBuilder`.
- `IrrigationPlanDirector`.
- Construcción encadenada del objeto.
- Método `build()`.
- Endpoint REST.
- Prueba JUnit.
- Resultado `BUILD SUCCESS`.

---

## 15. Diagrama UML

El diagrama UML se encuentra en:

`Semana 5 (Builder)/uml/Builder_AgroPrecision.png`

---

## 16. Conclusión

El patrón Builder fue implementado exitosamente dentro del microservicio `irrigation-service` de AgroPrecision.

Su utilización permite construir planes de riego complejos paso a paso, manteniendo un código más legible y desacoplado.

`IrrigationPlanBuilder` define el proceso de construcción, `AutomaticIrrigationPlanBuilder` realiza la construcción concreta, `IrrigationPlanDirector` coordina el proceso y `IrrigationPlan` representa el producto final.

La implementación fue comprobada mediante una API REST funcional y pruebas automatizadas, obteniendo `BUILD SUCCESS`.