# AgroPrecision - Entrega Primer Corte

Sistema de Agricultura de Precisión desarrollado con arquitectura de microservicios y patrones creacionales GoF.

## Patrones implementados

| Patrón | Módulo | Uso |
|---|---|---|
| Singleton | identity-service | Control centralizado de intentos fallidos de login |
| Factory Method | sensor-service | Creación de sensores de temperatura, humedad de suelo y humedad ambiental |
| Builder | irrigation-service | Construcción progresiva de `IrrigationPlan` |
| Abstract Factory | sensor-service | Familias compatibles LoRa/WiFi: Sensor + Gateway |
| Prototype | irrigation-service | Copia y reutilización de configuraciones de `IrrigationPlan` |

## Requisitos

- Java 21
- Node.js 20.19+ (el entorno de desarrollo utilizado dispone de Node 24)
- Git (si se clona el repositorio)
- Puertos 5173, 8080, 8081 y 8082 disponibles

## Ejecutar identity-service

```powershell
cd services\identity-service
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Puerto: `http://localhost:8080`

Credenciales demo:
- Correo: `admin@agroprecision.com`
- Contraseña: `Agro2026!`

## Ejecutar sensor-service

```powershell
cd services\sensor-service
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Puerto: `http://localhost:8081`

Endpoints principales:
- Factory Method: `GET /api/sensors/temperature`
- Factory Method: `GET /api/sensors/soil-moisture`
- Factory Method: `GET /api/sensors/air-humidity`
- Abstract Factory: `GET /api/device-families/lora`
- Abstract Factory: `GET /api/device-families/wifi`

## Ejecutar irrigation-service

```powershell
cd services\irrigation-service
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Puerto: `http://localhost:8082`

Endpoints principales:
- Builder: `GET /api/irrigation/automatic-plan?cropId=CROP-01&zone=A1`
- Prototype: `GET /api/irrigation/prototype-plan?cropId=CROP-02&zone=B1`

## Ejecutar frontend

```powershell
cd frontend
npm install
npm run dev
```

URL esperada: `http://localhost:5173`

## Validación de pruebas

Ejecutar `./mvnw.cmd test` dentro de cada microservicio. La entrega se considera validada cuando Maven muestra `Failures: 0`, `Errors: 0` y `BUILD SUCCESS`.

## Estructura de evidencias

- `Semana 3 (Singleton)`
- `Semana 4 (Factory Method)`
- `Semana 5 (Builder)`
- `Semana 6 (Abstract Factory)`
- `Semana 7 (Prototype)`

Cada carpeta semanal contiene documentación, UML y video de evidencia.
