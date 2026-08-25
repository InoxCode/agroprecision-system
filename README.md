# AgroPrecision

## Sistema de Agricultura de Precisión

AgroPrecision es un sistema de software orientado al sector agrícola cuyo propósito es apoyar el monitoreo, control y toma de decisiones relacionadas con la producción agrícola mediante el uso de datos provenientes de sensores IoT, información climática, inventarios y registros históricos.

El sistema busca centralizar información relevante de los cultivos y transformarla en información útil para el productor, permitiendo supervisar las condiciones de las parcelas, optimizar el uso del agua, controlar inventarios y cadena de frío, así como realizar estimaciones sobre futuras cosechas.

Este proyecto es desarrollado con fines académicos para la asignatura **Patrones de Diseño de Software** del programa de Ingeniería de Sistemas de las Unidades Tecnológicas de Santander - UTS.

---

## Problema

Los procesos agrícolas requieren tomar decisiones continuamente sobre variables como humedad del suelo, temperatura, condiciones climáticas, riego, almacenamiento, inventarios y producción.

Cuando esta información se administra de manera aislada o manual, pueden presentarse dificultades como:

- Uso ineficiente del agua.
- Falta de información en tiempo real sobre los cultivos.
- Pérdidas por condiciones inadecuadas de almacenamiento.
- Dificultades en el control de inventarios.
- Falta de trazabilidad de las condiciones de las parcelas.
- Dificultad para estimar futuras cosechas.
- Baja integración entre diferentes fuentes de información.

AgroPrecision propone integrar estos procesos dentro de una plataforma de software basada en una arquitectura moderna, desacoplada y mantenible.

---

## Objetivo general

Diseñar y desarrollar un Sistema de Agricultura de Precisión que permita recopilar, procesar, analizar y visualizar información relacionada con cultivos agrícolas, aplicando arquitectura de software, patrones de diseño GoF, pruebas automatizadas, integración continua y mecanismos de observabilidad.

---

## Objetivos específicos

- Implementar mecanismos de monitoreo de cultivos mediante información proveniente de sensores IoT y drones.
- Diseñar un sistema de riego automatizado basado en condiciones climáticas y variables del suelo.
- Gestionar inventarios relacionados con productos e insumos agrícolas.
- Supervisar condiciones asociadas con la cadena de frío.
- Registrar información histórica de las cosechas.
- Generar estimaciones de producción utilizando datos históricos.
- Aplicar patrones de diseño GoF en problemas reales del dominio.
- Diseñar una arquitectura basada en microservicios y principios de arquitectura hexagonal.
- Implementar pruebas automatizadas con una cobertura mínima del 80%.
- Aplicar control de versiones e integración continua mediante Git, GitHub y CI/CD.
- Implementar mecanismos de monitoreo, métricas y logging.
- Documentar las principales decisiones arquitectónicas mediante Architecture Decision Records (ADR).

---

# Alcance del sistema

AgroPrecision estará compuesto por diferentes módulos orientados a las principales necesidades de una operación agrícola.

El sistema permitirá inicialmente trabajar con información simulada de sensores IoT y drones, evitando depender de dispositivos físicos durante las primeras etapas del desarrollo.

Posteriormente, la arquitectura permitirá integrar dispositivos y servicios externos reales mediante adaptadores.

Los principales componentes funcionales serán:

1. Gestión de usuarios.
2. Gestión de fincas y parcelas.
3. Gestión de cultivos.
4. Gestión de sensores.
5. Monitoreo de cultivos.
6. Gestión de riego.
7. Integración de información climática.
8. Gestión de inventarios.
9. Gestión de cadena de frío.
10. Gestión de cosechas.
11. Análisis de datos históricos.
12. Predicción de producción.
13. Gestión de alertas y eventos.

---

# Módulos principales

## 1. Monitoreo de cultivos

Este módulo permitirá registrar y consultar información relacionada con el estado de las parcelas y los cultivos.

Entre las variables consideradas se encuentran:

- Humedad del suelo.
- Temperatura ambiental.
- Humedad ambiental.
- Estado del cultivo.
- Ubicación del sensor.
- Fecha y hora de las mediciones.
- Información proveniente de drones.

Durante las primeras fases del proyecto, las mediciones podrán ser generadas mediante sensores simulados.

---

## 2. Sistema de riego automatizado

El sistema analizará diferentes variables para determinar si un cultivo requiere riego.

Podrán considerarse variables como:

- Humedad del suelo.
- Temperatura.
- Condiciones climáticas.
- Tipo de cultivo.
- Necesidades hídricas.
- Historial de riego.

El sistema podrá generar recomendaciones y simular la activación o desactivación de mecanismos de riego.

---

## 3. Gestión de inventario

Permitirá administrar productos, insumos y elementos relacionados con la operación agrícola.

Entre sus funcionalidades se contemplan:

- Registro de productos.
- Registro de insumos.
- Entradas de inventario.
- Salidas de inventario.
- Control de existencias.
- Gestión de lotes.
- Fechas de almacenamiento.
- Alertas por niveles mínimos de inventario.

---

## 4. Gestión de cadena de frío

Permitirá registrar y supervisar las condiciones de almacenamiento de productos que requieran control de temperatura.

El módulo podrá gestionar:

- Temperatura de almacenamiento.
- Rangos permitidos.
- Historial de temperaturas.
- Alertas por temperaturas fuera de rango.
- Estado de los productos almacenados.
- Trazabilidad de lotes.

---

## 5. Gestión de cosechas

Permitirá registrar información relacionada con los resultados de producción.

Se podrán almacenar datos como:

- Cultivo.
- Parcela.
- Área cultivada.
- Fecha de siembra.
- Fecha de cosecha.
- Cantidad producida.
- Condiciones climáticas.
- Rendimiento por área.

---

## 6. Predicción de cosechas

El sistema utilizará datos históricos para generar estimaciones sobre futuras producciones.

Inicialmente podrán utilizarse modelos estadísticos básicos.

Entre las variables consideradas se encuentran:

- Producción histórica.
- Área cultivada.
- Tipo de cultivo.
- Temperatura promedio.
- Precipitaciones.
- Humedad.
- Temporada.
- Historial de producción.

En etapas posteriores podrá evaluarse la incorporación de técnicas de Machine Learning.

---

# Arquitectura del sistema

El proyecto será diseñado utilizando una combinación de:

- Arquitectura basada en microservicios.
- Arquitectura hexagonal.
- APIs REST.
- Comunicación desacoplada entre componentes.

La arquitectura buscará mantener la lógica del dominio independiente de tecnologías externas como bases de datos, interfaces gráficas, sensores, APIs climáticas o sistemas de mensajería.

---

## Arquitectura hexagonal

Cada servicio buscará organizarse aplicando los principios de arquitectura hexagonal, separando las responsabilidades en:

```text
                 Adaptadores de entrada
                         |
                         v
                    Puertos de entrada
                         |
                         v
                     Aplicación
                         |
                         v
                       Dominio
                         |
                         v
                    Puertos de salida
                         |
                         v
                 Adaptadores de salida
```

La lógica de negocio permanecerá aislada de tecnologías específicas.

Ejemplos de adaptadores externos:

- PostgreSQL.
- APIs meteorológicas.
- Sensores IoT.
- Drones.
- Servicios de notificación.
- Interfaces REST.

---

# Microservicios propuestos

La división definitiva de los microservicios será validada durante la fase de diseño arquitectónico.

Inicialmente se contemplan los siguientes servicios:

```text
AgroPrecision
|
|-- Identity Service
|
|-- Farm Management Service
|   |-- Fincas
|   |-- Parcelas
|   `-- Cultivos
|
|-- Monitoring Service
|   |-- Sensores
|   |-- Mediciones
|   `-- Alertas
|
|-- Irrigation Service
|   |-- Estrategias de riego
|   `-- Activaciones
|
|-- Inventory Service
|   |-- Inventario
|   |-- Productos
|   `-- Cadena de frío
|
`-- Harvest Service
    |-- Cosechas
    |-- Históricos
    `-- Predicciones
```

La cantidad y responsabilidad de cada microservicio podrá ser ajustada mediante decisiones documentadas en ADR.

---

# Patrones de diseño GoF

El proyecto deberá implementar como mínimo **8 patrones de diseño GoF**, incluyendo al menos dos patrones de cada una de las tres categorías establecidas por Gang of Four:

- Creacionales.
- Estructurales.
- De comportamiento.

Los patrones serán implementados únicamente cuando exista un problema de diseño que justifique su utilización.

---

## Patrones creacionales propuestos

### Factory Method

Permitirá crear diferentes tipos de sensores sin acoplar directamente la lógica de negocio a clases concretas.

Ejemplos:

- Sensor de temperatura.
- Sensor de humedad.
- Sensor de humedad del suelo.

---

### Abstract Factory

Permitirá crear familias relacionadas de dispositivos o componentes asociados con diferentes tipos de sistemas de monitoreo.

---

## Patrones estructurales propuestos

### Adapter

Permitirá integrar servicios externos manteniendo aislado el dominio.

Posibles aplicaciones:

- API meteorológica.
- Plataformas IoT.
- Drones.
- Servicios externos de información.

---

### Facade

Permitirá proporcionar interfaces simplificadas para operaciones que involucren múltiples componentes internos.

Una posible aplicación será el proceso completo de evaluación y ejecución del riego.

---

### Decorator

Permitirá agregar responsabilidades adicionales a determinados componentes sin modificar directamente su implementación.

Podrá utilizarse, por ejemplo, para enriquecer mecanismos de notificación o procesamiento de eventos.

---

## Patrones de comportamiento propuestos

### Observer

Permitirá reaccionar ante eventos producidos por sensores.

Ejemplo:

```text
Sensor
   |
   v
Nueva medición
   |
   v
Observer
   |
   +-- Actualizar monitoreo
   +-- Evaluar alerta
   `-- Evaluar riego
```

---

### Strategy

Permitirá implementar diferentes algoritmos o estrategias de riego.

Por ejemplo:

```text
IrrigationStrategy
|
|-- CoffeeIrrigationStrategy
|-- TomatoIrrigationStrategy
|-- CornIrrigationStrategy
`-- AvocadoIrrigationStrategy
```

Cada estrategia podrá determinar las condiciones necesarias para recomendar o activar el riego.

---

### State

Permitirá administrar los diferentes estados de determinados elementos del sistema.

Ejemplos:

```text
Sistema de riego:

OFF
READY
IRRIGATING
PAUSED
ERROR
```

También podrá utilizarse en sensores, inventarios o procesos de almacenamiento.

---

# Patrones GoF inicialmente considerados

| Categoría | Patrón | Posible aplicación |
|---|---|---|
| Creacional | Factory Method | Creación de sensores |
| Creacional | Abstract Factory | Familias de dispositivos |
| Estructural | Adapter | Integración de APIs y dispositivos |
| Estructural | Facade | Simplificación de procesos complejos |
| Estructural | Decorator | Extensión dinámica de funcionalidades |
| Comportamiento | Observer | Eventos de sensores |
| Comportamiento | Strategy | Algoritmos de riego |
| Comportamiento | State | Estados de dispositivos y procesos |

La selección podrá modificarse durante el desarrollo cuando el análisis arquitectónico identifique patrones más apropiados.

---

# Tecnologías propuestas

## Backend

- Java.
- Spring Boot.
- Spring Web.
- Spring Data.
- API REST.

---

## Frontend

- React.
- Vite.
- JavaScript o TypeScript.

---

## Base de datos

- PostgreSQL.

---

## Testing

Se implementarán pruebas automatizadas con una cobertura mínima requerida del **80%**.

Herramientas propuestas:

- JUnit.
- Mockito.
- Spring Boot Test.
- JaCoCo.

Las pruebas buscarán garantizar:

- Confianza en el código.
- Mantenibilidad.
- Detección temprana de errores.
- Refactorización segura.

---

## Control de versiones

- Git.
- GitHub.

---

## CI/CD

Se implementará un proceso de Integración Continua y Entrega Continua.

Herramienta propuesta:

- GitHub Actions.

El pipeline podrá ejecutar automáticamente:

```text
Push / Pull Request
        |
        v
Compilación
        |
        v
Pruebas automatizadas
        |
        v
Validación de cobertura
        |
        v
Análisis
        |
        v
Construcción
        |
        v
Despliegue
```

---

## Monitoreo y logging

El sistema deberá incorporar mecanismos de observabilidad para supervisar el comportamiento de los servicios.

Se evaluará el uso de:

- Prometheus.
- Grafana.

También podrán evaluarse alternativas como:

- ELK Stack:
  - Elasticsearch.
  - Logstash.
  - Kibana.
- Datadog.

La solución definitiva será seleccionada y documentada mediante un ADR.

---

## Contenedores

Durante las fases posteriores se utilizará:

- Docker.
- Docker Compose.

Esto permitirá ejecutar de manera reproducible los diferentes componentes del sistema.

---

## Herramientas de desarrollo

- IntelliJ IDEA o Visual Studio Code.
- Postman.
- Git.
- GitHub.
- Docker.
- Maven.

---

# Documentación UML

El proyecto incluirá documentación UML relacionada con el análisis, arquitectura e implementación del sistema.

Se contemplan como mínimo:

- Diagrama de casos de uso.
- Diagrama de clases.
- Diagrama de componentes.
- Diagramas de secuencia.
- Diagrama de despliegue.
- Diagramas asociados a los patrones de diseño implementados.

Los diagramas serán almacenados dentro del directorio de documentación del proyecto.

---

# Architecture Decision Records

Las decisiones arquitectónicas importantes serán documentadas mediante **Architecture Decision Records (ADR)**.

Cada ADR deberá registrar:

- Contexto.
- Problema.
- Alternativas consideradas.
- Decisión tomada.
- Justificación.
- Consecuencias.

Ejemplo:

```text
ADR-001 Selección de arquitectura
ADR-002 División inicial de microservicios
ADR-003 Selección de base de datos
ADR-004 Comunicación entre servicios
ADR-005 Estrategia de observabilidad
ADR-006 Estrategia de CI/CD
```

---

# Estructura propuesta del repositorio

```text
agroprecision-system/
|
|-- services/
|   |-- identity-service/
|   |-- farm-service/
|   |-- monitoring-service/
|   |-- irrigation-service/
|   |-- inventory-service/
|   `-- harvest-service/
|
|-- frontend/
|
|-- docs/
|   |-- requirements/
|   |-- architecture/
|   |-- uml/
|   |-- adr/
|   |-- patterns/
|   |-- testing/
|   `-- manuals/
|
|-- infrastructure/
|   |-- docker/
|   |-- monitoring/
|   `-- ci-cd/
|
|-- .github/
|   `-- workflows/
|
|-- .gitignore
`-- README.md
```

Esta estructura representa una propuesta inicial y podrá evolucionar durante el desarrollo.

---

# Fases del proyecto

## Fase 1 - Definición del proyecto

Actividades:

- Definición del problema.
- Justificación.
- Objetivos.
- Alcance.
- Restricciones.
- Identificación inicial de actores.
- Identificación de módulos.
- Creación del repositorio GitHub.
- Documentación inicial.

Estado: En desarrollo.

---

## Fase 2 - Análisis de requisitos

Se definirán:

- Requisitos funcionales.
- Requisitos no funcionales.
- Historias de usuario.
- Actores.
- Casos de uso.
- Criterios de aceptación.

Estado: Pendiente.

---

## Fase 3 - Diseño arquitectónico

Se definirán:

- Arquitectura general.
- Microservicios.
- Límites de contexto.
- Arquitectura hexagonal.
- Comunicación entre servicios.
- Modelo de dominio.
- Persistencia.
- Diagramas UML.
- ADR iniciales.

Estado: Pendiente.

---

## Fase 4 - Diseño de patrones

Se identificarán los problemas de diseño donde sea apropiado aplicar patrones GoF.

Para cada patrón se documentará:

1. Problema.
2. Contexto.
3. Patrón seleccionado.
4. Justificación.
5. Diagrama UML.
6. Implementación.
7. Pruebas.
8. Resultado obtenido.

Estado: Pendiente.

---

## Fase 5 - Implementación del núcleo

Se desarrollarán inicialmente las funcionalidades relacionadas con:

- Usuarios.
- Fincas.
- Parcelas.
- Cultivos.
- Sensores.
- Mediciones.

Estado: Pendiente.

---

## Fase 6 - Monitoreo y riego

Se desarrollarán:

- Monitoreo de cultivos.
- Procesamiento de mediciones.
- Alertas.
- Estrategias de riego.
- Simulación de activación del sistema de riego.

Estado: Pendiente.

---

## Fase 7 - Inventario y cadena de frío

Se implementarán:

- Productos.
- Inventario.
- Entradas y salidas.
- Lotes.
- Control de temperatura.
- Alertas.

Estado: Pendiente.

---

## Fase 8 - Cosechas y predicción

Se implementarán:

- Historial de cosechas.
- Registro de producción.
- Estadísticas.
- Estimaciones de producción.

Estado: Pendiente.

---

## Fase 9 - Pruebas automatizadas

Se implementarán:

- Pruebas unitarias.
- Pruebas de integración.
- Validación de servicios.
- Medición de cobertura.
- Cobertura mínima del 80%.

Estado: Pendiente.

---

## Fase 10 - CI/CD

Se configurará GitHub Actions para automatizar:

- Compilación.
- Ejecución de pruebas.
- Validación de cobertura.
- Construcción del proyecto.
- Validaciones de calidad.
- Procesos de despliegue.

Estado: Pendiente.

---

## Fase 11 - Observabilidad

Se implementarán:

- Logging.
- Métricas.
- Monitoreo.
- Dashboards.
- Alertas de infraestructura y servicios.

Estado: Pendiente.

---

## Fase 12 - Despliegue

Se preparará el sistema utilizando tecnologías de contenerización y despliegue.

Se contempla:

- Docker.
- Docker Compose.
- Configuración de entornos.
- Variables de entorno.
- Despliegue de servicios.

Estado: Pendiente.

---

## Fase 13 - Documentación final

Se completarán:

- Documentación técnica.
- UML.
- ADR.
- Manuales.
- Documentación de patrones.
- Documentación de pruebas.
- Documentación del despliegue.

Estado: Pendiente.

---

# Entregables académicos

El proyecto deberá incluir los siguientes entregables:

1. Repositorio GitHub con el código fuente.
2. Documentación técnica.
3. Architecture Decision Records (ADR).
4. Diagramas UML.
5. Manuales.
6. Video demostrativo de 5 a 7 minutos.
7. Presentación ejecutiva.
8. Portfolio individual.

---

# Estrategia de desarrollo

El desarrollo será incremental.

Se buscará construir primero un núcleo funcional pequeño y posteriormente incorporar los demás módulos.

La estrategia general será:

```text
Definición
    |
    v
Requisitos
    |
    v
Arquitectura
    |
    v
UML
    |
    v
Modelo de dominio
    |
    v
Microservicios
    |
    v
Patrones GoF
    |
    v
Implementación
    |
    v
Testing
    |
    v
CI/CD
    |
    v
Observabilidad
    |
    v
Despliegue
```

---

# Estado actual

Actualmente el proyecto se encuentra en la etapa de definición y documentación inicial.

Avances:

- [x] Definición inicial del proyecto.
- [x] Creación del repositorio GitHub.
- [x] README inicial.
- [x] Definición preliminar de tecnologías.
- [x] Identificación de requisitos técnicos académicos.
- [ ] Requisitos funcionales.
- [ ] Requisitos no funcionales.
- [ ] Historias de usuario.
- [ ] Casos de uso.
- [ ] Arquitectura detallada.
- [ ] Diagramas UML.
- [ ] ADR.
- [ ] Implementación.
- [ ] Pruebas automatizadas.
- [ ] CI/CD.
- [ ] Observabilidad.
- [ ] Despliegue.

---

# Autor

Proyecto académico desarrollado para la asignatura **Patrones de Diseño de Software**.

Programa: Ingeniería de Sistemas.

Institución: Unidades Tecnológicas de Santander - UTS.

Docente: Eliecer Montero Ojeda Ed.D.

---

# Licencia

Proyecto desarrollado con fines académicos y educativos.
