# Semana 6 - Implementación del Patrón Abstract Factory

## Proyecto

**AgroPrecision - Sistema de Agricultura de Precisión**

**Asignatura:** Patrones de Diseño de Software  
**Patrón implementado:** Abstract Factory  
**Categoría GoF:** Creacional  

---

## 1. Descripción del patrón

Abstract Factory es un patrón de diseño creacional perteneciente al catálogo GoF.

Su objetivo es proporcionar una interfaz para crear familias de objetos relacionados o compatibles sin que el código cliente dependa directamente de sus clases concretas.

En AgroPrecision el patrón fue implementado dentro del microservicio `sensor-service` para crear familias compatibles de dispositivos IoT.

Las familias implementadas son:

- Dispositivos LoRa.
- Dispositivos WiFi.

Cada familia contiene:

- Un sensor IoT.
- Un gateway.

---

## 2. Justificación del uso

Abstract Factory es conveniente para AgroPrecision porque un sistema de agricultura de precisión puede utilizar diferentes tecnologías de comunicación para sus dispositivos IoT.

Por ejemplo, una instalación basada en LoRa requiere dispositivos compatibles entre sí:

```text
LoRaSensor
+
LoRaGateway
```

Mientras que una instalación WiFi utiliza:

```text
WiFiSensor
+
WiFiGateway
```

El patrón permite seleccionar una familia tecnológica completa y evita que el código cliente tenga que crear directamente cada implementación concreta.

De esta manera se reduce el acoplamiento y se facilita la incorporación futura de nuevas familias tecnológicas.

---

## 3. Diferencia con Factory Method

Anteriormente AgroPrecision implementó Factory Method para crear distintos tipos de sensores.

Factory Method se utiliza principalmente para decidir qué producto concreto crear.

Abstract Factory, en cambio, permite crear una familia completa de productos relacionados.

```text
Factory Method
→ crea un tipo concreto de Sensor.

Abstract Factory
→ crea una familia compatible:
   Sensor + Gateway.
```

Por esta razón ambos patrones cumplen responsabilidades diferentes dentro de `sensor-service`.

---

## 4. Abstract Products

Se definieron dos productos abstractos.

### IoTSensor

La interfaz:

`IoTSensor`

representa un sensor IoT perteneciente a una familia tecnológica.

```java
public interface IoTSensor {

    String getTechnology();

    String getDescription();
}
```

### Gateway

La interfaz:

`Gateway`

representa el dispositivo encargado de recibir o centralizar los datos enviados por los sensores.

```java
public interface Gateway {

    String getTechnology();

    String getDescription();
}
```

---

## 5. Productos concretos

Se implementaron dos familias de productos.

### Familia LoRa

```text
LoRaSensor
LoRaGateway
```

`LoRaSensor` implementa `IoTSensor`.

`LoRaGateway` implementa `Gateway`.

Ambos utilizan la tecnología:

```text
LoRa
```

### Familia WiFi

```text
WiFiSensor
WiFiGateway
```

`WiFiSensor` implementa `IoTSensor`.

`WiFiGateway` implementa `Gateway`.

Ambos utilizan la tecnología:

```text
WiFi
```

---

## 6. Abstract Factory

La interfaz:

`IoTDeviceFactory`

representa la fábrica abstracta.

Ubicación:

`services/sensor-service/src/main/java/com/agroprecision/sensor/application/abstractfactory/IoTDeviceFactory.java`

Código:

```java
public interface IoTDeviceFactory {

    IoTSensor createSensor();

    Gateway createGateway();
}
```

La fábrica define la creación de los dos productos que forman una familia tecnológica:

```text
IoTSensor
+
Gateway
```

---

## 7. Concrete Factories

### LoRaDeviceFactory

La fábrica:

`LoRaDeviceFactory`

crea exclusivamente productos pertenecientes a la familia LoRa.

```java
public class LoRaDeviceFactory implements IoTDeviceFactory {

    @Override
    public IoTSensor createSensor() {
        return new LoRaSensor();
    }

    @Override
    public Gateway createGateway() {
        return new LoRaGateway();
    }
}
```

Resultado:

```text
LoRaDeviceFactory
├── LoRaSensor
└── LoRaGateway
```

### WiFiDeviceFactory

La fábrica:

`WiFiDeviceFactory`

crea productos pertenecientes a la familia WiFi.

```java
public class WiFiDeviceFactory implements IoTDeviceFactory {

    @Override
    public IoTSensor createSensor() {
        return new WiFiSensor();
    }

    @Override
    public Gateway createGateway() {
        return new WiFiGateway();
    }
}
```

Resultado:

```text
WiFiDeviceFactory
├── WiFiSensor
└── WiFiGateway
```

---

## 8. Integración mediante API REST

El patrón fue integrado mediante:

`DeviceFamilyController`

Ubicación:

`services/sensor-service/src/main/java/com/agroprecision/sensor/infrastructure/adapter/in/rest/DeviceFamilyController.java`

El controlador selecciona la fábrica correspondiente según la tecnología solicitada.

Después utiliza únicamente la abstracción:

```java
IoTDeviceFactory factory = getFactory(technology);

IoTSensor sensor = factory.createSensor();

Gateway gateway = factory.createGateway();
```

De esta manera el código cliente trabaja con `IoTDeviceFactory`, `IoTSensor` y `Gateway` en lugar de depender directamente de las implementaciones concretas.

---

## 9. Endpoints implementados

### Familia LoRa

```text
GET http://localhost:8081/api/device-families/lora
```

Ejemplo de respuesta:

```json
{
  "technology": "LoRa",
  "sensor": "Sensor IoT LoRa para monitoreo agrícola de largo alcance.",
  "gateway": "Gateway LoRa para recibir datos de sensores agrícolas."
}
```

### Familia WiFi

```text
GET http://localhost:8081/api/device-families/wifi
```

Ejemplo:

```json
{
  "technology": "WiFi",
  "sensor": "Sensor IoT WiFi para monitoreo agrícola mediante red inalámbrica.",
  "gateway": "Gateway WiFi para centralizar datos de sensores agrícolas."
}
```

---

## 10. Flujo de funcionamiento

```text
DeviceFamilyController
          |
          v
    IoTDeviceFactory
          |
     +----+----+
     |         |
     v         v
LoRaFactory  WiFiFactory
     |         |
     v         v
Sensor      Sensor
Gateway     Gateway
```

---

## 11. Pruebas automatizadas

Se implementó:

`IoTDeviceFactoryTest`

Las pruebas verifican que cada fábrica cree los productos correspondientes a su propia familia.

Ejemplo LoRa:

```java
IoTDeviceFactory factory =
        new LoRaDeviceFactory();

var sensor =
        factory.createSensor();

var gateway =
        factory.createGateway();

assertInstanceOf(
        LoRaSensor.class,
        sensor
);

assertInstanceOf(
        LoRaGateway.class,
        gateway
);
```

También se comprueba que ambos productos utilicen la misma tecnología:

```java
assertEquals(
        sensor.getTechnology(),
        gateway.getTechnology()
);
```

Esto permite verificar:

```text
LoRaSensor + LoRaGateway ✓
WiFiSensor + WiFiGateway ✓
```

---

## 12. Resultado de las pruebas

Las pruebas fueron ejecutadas utilizando Maven:

```text
.\mvnw.cmd test
```

El proyecto obtuvo:

```text
Failures: 0
Errors: 0

BUILD SUCCESS
```

---

## 13. Beneficios para AgroPrecision

Abstract Factory permite:

- Crear familias completas de dispositivos compatibles.
- Reducir el acoplamiento con las clases concretas.
- Cambiar la tecnología utilizada sin modificar la lógica cliente.
- Evitar combinaciones incompatibles de productos.
- Facilitar la incorporación de nuevas tecnologías.
- Centralizar la creación de dispositivos relacionados.

En el futuro podrían incorporarse familias como:

```text
Zigbee
NB-IoT
Bluetooth Low Energy
5G IoT
```

implementando nuevas fábricas sin modificar el contrato principal.

---

## 14. Evidencia en video

La evidencia del patrón se encuentra en:

`Semana 6 (Abstract Factory)/video/AbstractFactory_AgroPrecision_Semana6.mov`

---

## 15. Diagrama UML

El UML correspondiente se encuentra en:

`Semana 6 (Abstract Factory)/uml/AbstractFactory_AgroPrecision.png`

---

## 16. Conclusión

Abstract Factory fue implementado exitosamente dentro del microservicio `sensor-service` de AgroPrecision.

El patrón permite crear familias compatibles de dispositivos IoT mediante una fábrica abstracta común.

`LoRaDeviceFactory` genera sensores y gateways LoRa, mientras que `WiFiDeviceFactory` genera productos equivalentes utilizando tecnología WiFi.

La implementación permite mantener desacoplado el código cliente y facilita incorporar nuevas familias tecnológicas en futuras versiones de AgroPrecision.