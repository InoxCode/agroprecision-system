# AgroPrecision

## Sistema de Agricultura de Precisión

AgroPrecision es una aplicación de software orientada al sector agrícola cuyo objetivo es apoyar la toma de decisiones mediante el monitoreo de cultivos, sensores IoT, información climática, gestión de inventarios y análisis de datos históricos.

El sistema busca centralizar información relevante de los cultivos para permitir al productor conocer el estado de sus parcelas, optimizar el uso del agua, controlar productos almacenados y estimar la producción de futuras cosechas.

Este proyecto es desarrollado como parte de la asignatura **Patrones de Software**.

---

## Objetivo general

Diseñar y desarrollar un sistema de agricultura de precisión que permita recopilar, procesar y visualizar información relacionada con cultivos agrícolas, utilizando principios de arquitectura de software y patrones de diseño para construir una solución modular, mantenible y escalable.

---

## Objetivos específicos

- Implementar un módulo de monitoreo de cultivos mediante datos provenientes de sensores IoT y drones.
- Diseñar un sistema de riego automatizado basado en condiciones climáticas y variables del suelo.
- Gestionar inventarios de productos agrícolas.
- Supervisar variables relacionadas con la cadena de frío.
- Analizar datos históricos de producción.
- Generar estimaciones de producción de futuras cosechas.
- Aplicar patrones de diseño de software para solucionar problemas recurrentes dentro del sistema.
- Implementar una arquitectura modular que facilite el mantenimiento y evolución de la aplicación.

---

# Módulos principales

## 1. Monitoreo de cultivos

Permitirá registrar y consultar información relacionada con el estado de los cultivos.

Variables consideradas:

- Humedad del suelo
- Temperatura
- Humedad ambiental
- Estado del cultivo
- Ubicación de sensores
- Información obtenida mediante drones

Inicialmente los datos de sensores y drones podrán ser simulados dentro del sistema.

---

## 2. Sistema de riego automatizado

El sistema analizará diferentes variables para determinar cuándo un cultivo necesita riego.

Entre las variables se consideran:

- Humedad del suelo
- Temperatura
- Pronóstico climático
- Tipo de cultivo
- Necesidades hídricas

El sistema podrá generar recomendaciones o activar de manera simulada un sistema de riego.

---

## 3. Gestión de inventario y cadena de frío

Permitirá administrar los productos e insumos relacionados con la actividad agrícola.

Funciones principales:

- Registro de productos
- Control de cantidades
- Entradas y salidas de inventario
- Control de lotes
- Registro de fechas de almacenamiento
- Monitoreo de temperatura
- Alertas por temperaturas fuera del rango permitido

---

## 4. Predicción de cosechas

Permitirá utilizar información histórica para realizar estimaciones sobre la producción esperada de los cultivos.

Se podrán considerar variables como:

- Producción histórica
- Área cultivada
- Tipo de cultivo
- Temperatura promedio
- Precipitaciones
- Humedad
- Temporada de cultivo

En las primeras versiones se utilizarán modelos estadísticos básicos. Posteriormente el módulo podrá evolucionar hacia modelos de Machine Learning.

---

# Arquitectura propuesta

El proyecto utilizará inicialmente una arquitectura de **monolito modular**.

La aplicación estará dividida en módulos independientes relacionados con las diferentes funcionalidades del sistema.

Módulos previstos:

- Cultivos
- Parcelas
- Sensores
- Monitoreo
- Riego
- Clima
- Inventario
- Cadena de frío
- Cosechas
- Predicciones
- Usuarios

La arquitectura buscará mantener separadas las responsabilidades entre:

- Presentación
- Lógica de negocio
- Acceso a datos
- Integraciones externas

---

# Patrones de diseño

Durante el desarrollo se evaluará la aplicación de diferentes patrones de software según las necesidades del sistema.

Algunos patrones candidatos son:

### Observer

Para gestionar eventos provenientes de sensores y generar alertas cuando determinadas variables cambien.

Ejemplo:

Sensor de temperatura → cambio de temperatura → notificación al sistema.

### Strategy

Para implementar diferentes estrategias de riego dependiendo del tipo de cultivo o de las condiciones climáticas.

### Factory Method

Para facilitar la creación de diferentes tipos de sensores.

Ejemplo:

- Sensor de temperatura
- Sensor de humedad
- Sensor de suelo

### Adapter

Para integrar servicios externos como APIs meteorológicas, drones o plataformas IoT.

### Repository

Para separar la lógica de negocio del acceso a la base de datos.

### State

Para administrar diferentes estados de dispositivos, cultivos, inventarios o sistemas de riego.

---

# Tecnologías propuestas

## Backend

- Java
- Spring Boot
- API REST

## Frontend

- React
- Vite
- JavaScript / TypeScript

## Base de datos

- PostgreSQL

## Control de versiones

- Git
- GitHub

## Herramientas

- Visual Studio Code / IntelliJ IDEA
- Postman
- Docker (etapas posteriores)

---

# Estructura inicial del proyecto

```text
agroprecision-system/

├── backend/
├── frontend/
├── database/
├── docs/
│   ├── requirements/
│   ├── architecture/
│   ├── diagrams/
│   └── patterns/
│
├── .github/
├── .gitignore
└── README.md
