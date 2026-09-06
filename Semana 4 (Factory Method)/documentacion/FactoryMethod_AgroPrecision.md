# Semana 4 - Implementación del Patrón Factory Method

## Proyecto

**AgroPrecision - Sistema de Agricultura de Precisión**

**Asignatura:** Patrones de Diseño de Software  
**Patrón implementado:** Factory Method  
**Categoría GoF:** Creacional  

---

## 1. Descripción del patrón

Factory Method es un patrón de diseño creacional perteneciente a los patrones GoF.

Su objetivo es definir una interfaz o clase base para la creación de objetos, permitiendo que las clases derivadas determinen qué tipo concreto de objeto será instanciado.

De esta forma, el código que utiliza los objetos puede trabajar con abstracciones en lugar de depender directamente de clases concretas.

En AgroPrecision, Factory Method fue implementado dentro del microservicio `sensor-service` para permitir la creación de diferentes tipos de sensores IoT utilizados en el monitoreo agrícola.

---

## 2. Problema identificado

El sistema AgroPrecision necesita trabajar con diferentes tipos de sensores IoT, por ejemplo:

- Sensores de temperatura.
- Sensores de humedad del suelo.
- Sensores de humedad ambiental.

Cada sensor posee características particulares, pero todos representan el mismo concepto general: un sensor utilizado para obtener información del entorno agrícola.

Crear directamente cada objeto desde el código principal produciría una dependencia fuerte entre el sistema y las clases concretas.

Por ejemplo:

```java
TemperatureSensor sensor = new TemperatureSensor();
```

Este tipo de implementación dificulta la extensión del sistema cuando sea necesario agregar nuevos tipos de sensores.

Para evitar este acoplamiento se implementó Factory Method.

---

## 3. Microservicio utilizado

Para implementar este patrón se creó un nuevo microservicio:

`sensor-service`

Ubicación dentro del proyecto:

`services/sensor-service`

El microservicio utiliza:

- Java 21.
- Spring Boot.
- Maven.
- Spring Web.
- Validation.

El servicio se ejecuta en:

```text
http://localhost:8081
```

La configuración se encuentra en:

`services/sensor-service/src/main/resources/application.properties`

```properties
spring.application.name=sensor-service
server.port=8081
```

---

## 4. Producto abstracto

Se creó la interfaz:

`Sensor`

Ubicación:

`services/sensor-service/src/main/java/com/agroprecision/sensor/domain/model/Sensor.java`

Código:

```java
public interface Sensor {

    String getType();

    String getUnit();

    String getDescription();
}
```

Esta interfaz representa el producto abstracto del patrón Factory Method.

Todos los sensores concretos deben implementar este contrato.

---

## 5. Productos concretos

Actualmente AgroPrecision dispone de tres implementaciones de `Sensor`.

### 5.1 TemperatureSensor

Representa un sensor IoT utilizado para monitorear la temperatura.

```java
public class TemperatureSensor implements Sensor {

    @Override
    public String getType() {
        return "Temperatura";
    }

    @Override
    public String getUnit() {
        return "°C";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT para monitorear la temperatura del cultivo.";
    }
}
```

---

### 5.2 SoilMoistureSensor

Representa un sensor encargado de monitorear la humedad del suelo.

```java
public class SoilMoistureSensor implements Sensor {

    @Override
    public String getType() {
        return "Humedad del suelo";
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT para monitorear la humedad presente en el suelo.";
    }
}
```

---

### 5.3 AirHumiditySensor

Representa un sensor utilizado para monitorear la humedad relativa del ambiente.

```java
public class AirHumiditySensor implements Sensor {

    @Override
    public String getType() {
        return "Humedad ambiental";
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT para monitorear la humedad relativa del ambiente.";
    }
}
```

---

## 6. Creator del patrón Factory Method

La clase abstracta:

`SensorCreator`

representa el Creator del patrón.

Ubicación:

`services/sensor-service/src/main/java/com/agroprecision/sensor/application/factory/SensorCreator.java`

Código:

```java
public abstract class SensorCreator {

    public abstract Sensor createSensor();
}
```

El método:

```java
createSensor()
```

corresponde al Factory Method.

La clase base define que debe crearse un objeto de tipo `Sensor`, pero no determina cuál será su implementación concreta.

---

## 7. Creators concretos

Cada tipo de sensor posee su propio creador.

### 7.1 TemperatureSensorCreator

```java
public class TemperatureSensorCreator extends SensorCreator {

    @Override
    public Sensor createSensor() {
        return new TemperatureSensor();
    }
}
```

Este Creator produce objetos:

`TemperatureSensor`

---

### 7.2 SoilMoistureSensorCreator

```java
public class SoilMoistureSensorCreator extends SensorCreator {

    @Override
    public Sensor createSensor() {
        return new SoilMoistureSensor();
    }
}
```

Este Creator produce objetos:

`SoilMoistureSensor`

---

### 7.3 AirHumiditySensorCreator

```java
public class AirHumiditySensorCreator extends SensorCreator {

    @Override
    public Sensor createSensor() {
        return new AirHumiditySensor();
    }
}
```

Este Creator produce objetos:

`AirHumiditySensor`

---

## 8. Estructura del Factory Method

La estructura implementada puede representarse de la siguiente manera:

```text
                    SensorCreator
                    <<abstract>>
                         |
                         |
                    createSensor()
                         |
          +--------------+--------------+
          |              |              |
          v              v              v
 Temperature       SoilMoisture     AirHumidity
 SensorCreator     SensorCreator    SensorCreator
          |              |              |
          v              v              v
 Temperature       SoilMoisture     AirHumidity
    Sensor             Sensor           Sensor
```

Existe una jerarquía de creadores y una jerarquía de productos.

### Productos

```text
Sensor
├── TemperatureSensor
├── SoilMoistureSensor
└── AirHumiditySensor
```

### Creadores

```text
SensorCreator
├── TemperatureSensorCreator
├── SoilMoistureSensorCreator
└── AirHumiditySensorCreator
```

---

## 9. Integración mediante API REST

El patrón fue integrado mediante:

`SensorController`

Ubicación:

`services/sensor-service/src/main/java/com/agroprecision/sensor/infrastructure/adapter/in/rest/SensorController.java`

El controlador selecciona el Creator correspondiente según el tipo solicitado.

Posteriormente utiliza:

```java
SensorCreator creator = getCreator(type);

Sensor sensor = creator.createSensor();
```

La línea:

```java
creator.createSensor();
```

ejecuta el Factory Method.

El controlador trabaja con las abstracciones:

```java
SensorCreator
```

y:

```java
Sensor
```

en lugar de depender directamente del producto concreto después de seleccionar el Creator.

---

## 10. Selección del Creator

El controlador permite seleccionar el Creator mediante el tipo enviado en la URL.

```java
return switch (type.toLowerCase()) {

    case "temperature" ->
            new TemperatureSensorCreator();

    case "soil-moisture" ->
            new SoilMoistureSensorCreator();

    case "air-humidity" ->
            new AirHumiditySensorCreator();

    default ->
            throw new IllegalArgumentException(
                    "Tipo de sensor no soportado: " + type
            );
};
```

Este `switch` selecciona qué Creator será utilizado.

El Factory Method propiamente dicho continúa siendo:

```java
createSensor()
```

implementado por cada Creator concreto.

---

## 11. Endpoints implementados

### Sensor de temperatura

```text
GET http://localhost:8081/api/sensors/temperature
```

Ejemplo de respuesta:

```json
{
  "type": "Temperatura",
  "unit": "°C",
  "description": "Sensor IoT para monitorear la temperatura del cultivo."
}
```

---

### Sensor de humedad del suelo

```text
GET http://localhost:8081/api/sensors/soil-moisture
```

Ejemplo de respuesta:

```json
{
  "type": "Humedad del suelo",
  "unit": "%",
  "description": "Sensor IoT para monitorear la humedad presente en el suelo."
}
```

---

### Sensor de humedad ambiental

```text
GET http://localhost:8081/api/sensors/air-humidity
```

Ejemplo de respuesta:

```json
{
  "type": "Humedad ambiental",
  "unit": "%",
  "description": "Sensor IoT para monitorear la humedad relativa del ambiente."
}
```

---

## 12. Pruebas automatizadas

Se implementó la clase:

`SensorCreatorTest`

Ubicación:

`services/sensor-service/src/test/java/com/agroprecision/sensor/application/factory/SensorCreatorTest.java`

Las pruebas verifican que cada Creator genere el producto concreto correspondiente.

Ejemplo:

```java
SensorCreator creator =
        new TemperatureSensorCreator();

Sensor sensor =
        creator.createSensor();

assertInstanceOf(TemperatureSensor.class, sensor);
```

La prueba comprueba que:

```text
TemperatureSensorCreator
```

produce realmente:

```text
TemperatureSensor
```

También existen pruebas equivalentes para:

- `SoilMoistureSensorCreator`.
- `AirHumiditySensorCreator`.

---

## 13. Resultado de las pruebas

Las pruebas fueron ejecutadas mediante Maven utilizando:

```text
.\mvnw.cmd test
```

El resultado obtenido fue:

```text
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

Esto confirma que la implementación compila correctamente y que los Creators generan los sensores esperados.

---

## 14. Beneficios aportados a AgroPrecision

La implementación de Factory Method permite:

- Reducir el acoplamiento entre el código cliente y las clases concretas.
- Trabajar mediante la abstracción `Sensor`.
- Delegar la creación de objetos a clases especializadas.
- Facilitar la incorporación de nuevos sensores.
- Separar la lógica de creación de la lógica de utilización.
- Mantener una estructura más extensible y organizada.
- Aplicar un patrón GoF directamente a un caso de agricultura de precisión.

Por ejemplo, en el futuro podrían agregarse:

- Sensor de luminosidad.
- Sensor de pH.
- Sensor de conductividad eléctrica.
- Sensor de precipitación.
- Sensor de velocidad del viento.

Cada nuevo tipo podría disponer de su propio Creator.

---

## 15. Relación con la arquitectura de AgroPrecision

Factory Method fue implementado dentro de `sensor-service`, un microservicio separado del servicio de identidad.

La arquitectura actual permite distinguir:

```text
Frontend React
      |
      +---------------------------+
      |                           |
      v                           v
identity-service             sensor-service
Puerto 8080                   Puerto 8081
      |                           |
Autenticación                Sensores IoT
Singleton                    Factory Method
```

De esta manera, cada patrón se encuentra asociado a una responsabilidad concreta del sistema.

---

## 16. Evidencia en video

La demostración del patrón Factory Method se encuentra en:

`Semana 4 (Factory Method)/video/FactoryMethod_AgroPrecision_Semana4.mp4`

En el video se mostrará:

- La interfaz `Sensor`.
- La clase abstracta `SensorCreator`.
- El método `createSensor()`.
- Los Creators concretos.
- Los productos concretos.
- La creación de diferentes sensores.
- Los endpoints REST.
- Las pruebas automatizadas.
- El resultado `BUILD SUCCESS`.

---

## 17. Diagrama UML

El diagrama UML correspondiente al patrón se encuentra en:

`Semana 4 (Factory Method)/uml/FactoryMethod_AgroPrecision.png`

El diagrama representa las relaciones entre:

- `Sensor`.
- `TemperatureSensor`.
- `SoilMoistureSensor`.
- `AirHumiditySensor`.
- `SensorCreator`.
- `TemperatureSensorCreator`.
- `SoilMoistureSensorCreator`.
- `AirHumiditySensorCreator`.

---

## 18. Conclusión

El patrón Factory Method fue implementado exitosamente en AgroPrecision dentro del microservicio `sensor-service`.

La implementación permite crear diferentes tipos de sensores IoT mediante Creators especializados, manteniendo al sistema desacoplado de los productos concretos.

El método `createSensor()` actúa como Factory Method y es sobrescrito por los diferentes Creators para determinar qué sensor debe ser instanciado.

La implementación fue comprobada mediante endpoints REST y pruebas automatizadas con JUnit, obteniendo un resultado de:

`BUILD SUCCESS`

Con esta implementación, AgroPrecision dispone de una estructura extensible para incorporar nuevos tipos de sensores IoT conforme aumenten las necesidades del sistema de agricultura de precisión.