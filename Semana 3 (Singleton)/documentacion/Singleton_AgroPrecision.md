# Semana 3 - Implementación del Patrón Singleton

## Proyecto

**AgroPrecision - Sistema de Agricultura de Precisión**

**Asignatura:** Patrones de Diseño de Software  
**Patrón implementado:** Singleton  
**Categoría GoF:** Creacional  

---

## 1. Descripción del patrón

Singleton es un patrón de diseño creacional perteneciente a los patrones GoF.

Su objetivo es garantizar que una clase tenga una única instancia durante la ejecución de la aplicación y proporcionar un punto de acceso controlado a dicha instancia.

En el proyecto AgroPrecision, el patrón Singleton fue implementado dentro del microservicio de identidad y autenticación para administrar los intentos fallidos de inicio de sesión.

---

## 2. Problema identificado

El módulo de autenticación de AgroPrecision necesita llevar un registro de los intentos fallidos realizados por cada usuario.

Si cada solicitud de inicio de sesión creara un nuevo administrador de intentos, cada objeto tendría su propio contador independiente.

Esto impediría mantener correctamente el número de intentos entre diferentes solicitudes y dificultaría determinar cuándo un usuario debe ser bloqueado.

Por esta razón se requiere un único componente encargado de administrar los intentos fallidos de autenticación durante la ejecución del microservicio.

---

## 3. Solución implementada

Se implementó el patrón Singleton mediante la clase:

`LoginAttemptManager`

Ubicación dentro del proyecto:

`services/identity-service/src/main/java/com/agroprecision/identity/infrastructure/security/LoginAttemptManager.java`

Esta clase se encarga de:

- Registrar intentos fallidos de inicio de sesión.
- Consultar la cantidad de intentos realizados por un usuario.
- Restablecer los intentos cuando el inicio de sesión es exitoso.
- Determinar si un usuario debe ser bloqueado.
- Mantener un único administrador de intentos durante la ejecución del microservicio.

El sistema permite un máximo de tres intentos fallidos antes de bloquear al usuario.

---

## 4. Implementación del patrón Singleton

La implementación utiliza los principales elementos que caracterizan al patrón Singleton.

### 4.1 Instancia única

```java
private static final LoginAttemptManager INSTANCE =
        new LoginAttemptManager();
```

La variable `INSTANCE` es estática, por lo que pertenece directamente a la clase.

Esta instancia es creada una única vez y será compartida por los componentes que necesiten utilizar `LoginAttemptManager`.

---

### 4.2 Constructor privado

```java
private LoginAttemptManager() {
    this.attempts = new ConcurrentHashMap<>();
}
```

El constructor se declara como `private`.

Esto evita que otras clases puedan crear nuevas instancias mediante:

```java
new LoginAttemptManager();
```

De esta forma, la propia clase controla la creación de su única instancia.

---

### 4.3 Método de acceso a la instancia

```java
public static LoginAttemptManager getInstance() {
    return INSTANCE;
}
```

El método `getInstance()` permite obtener la instancia de `LoginAttemptManager`.

Cada vez que otra clase llama este método, obtiene exactamente la misma instancia.

---

## 5. Manejo de los intentos de inicio de sesión

Los intentos fallidos se almacenan mediante un `ConcurrentHashMap`.

```java
private final ConcurrentMap<String, Integer> attempts;
```

Cada correo electrónico es asociado con la cantidad de intentos fallidos realizados.

Ejemplo:

```text
admin@agroprecision.com -> 1 intento
admin@agroprecision.com -> 2 intentos
admin@agroprecision.com -> 3 intentos
```

Cuando el usuario alcanza tres intentos fallidos, el sistema determina que debe ser bloqueado.

```java
public boolean isBlocked(String email) {
    return getFailedAttempts(email) >= MAX_ATTEMPTS;
}
```

---

## 6. Integración con AgroPrecision

El patrón Singleton se utiliza directamente dentro del servicio de autenticación:

`AuthService`

Ubicación:

`services/identity-service/src/main/java/com/agroprecision/identity/application/service/AuthService.java`

La instancia se obtiene mediante:

```java
this.attemptManager =
        LoginAttemptManager.getInstance();
```

De esta manera, `AuthService` no crea un nuevo administrador de intentos.

En su lugar, obtiene la única instancia disponible de `LoginAttemptManager`.

El flujo general de autenticación es:

```text
Frontend React
      |
      v
POST /api/auth/login
      |
      v
AuthController
      |
      v
LoginUseCase
      |
      v
AuthService
      |
      v
LoginAttemptManager
   (Singleton)
```

---

## 7. Funcionamiento del patrón

Cuando el usuario introduce una contraseña incorrecta, `AuthService` registra el intento mediante:

```java
attemptManager.registerFailedAttempt(normalizedEmail);
```

El comportamiento observado es:

```text
Primer intento incorrecto
        |
        v
Intentos fallidos: 1 de 3

Segundo intento incorrecto
        |
        v
Intentos fallidos: 2 de 3

Tercer intento incorrecto
        |
        v
Intentos fallidos: 3 de 3
        |
        v
Usuario bloqueado
```

Las diferentes solicitudes comparten el mismo contador porque todas utilizan la misma instancia de `LoginAttemptManager`.

---

## 8. Prueba automatizada

Para comprobar el comportamiento del patrón Singleton se implementó una prueba automatizada utilizando JUnit.

Archivo:

`LoginAttemptManagerTest.java`

Ubicación:

`services/identity-service/src/test/java/com/agroprecision/identity/infrastructure/security/LoginAttemptManagerTest.java`

La prueba obtiene dos referencias de la clase:

```java
LoginAttemptManager first =
        LoginAttemptManager.getInstance();

LoginAttemptManager second =
        LoginAttemptManager.getInstance();
```

Posteriormente se utiliza:

```java
assertSame(first, second);
```

El método `assertSame` verifica que ambas variables apunten exactamente al mismo objeto en memoria.

Esto permite comprobar que `getInstance()` no está creando diferentes objetos y que la implementación cumple con el objetivo principal del patrón Singleton.

La prueba fue ejecutada mediante Maven y finalizó correctamente con:

```text
BUILD SUCCESS
```

---

## 9. Beneficios aportados al proyecto

La utilización de Singleton en AgroPrecision aporta los siguientes beneficios:

- Centraliza el control de intentos fallidos de autenticación.
- Evita la creación de múltiples administradores con estados independientes.
- Permite compartir el contador entre diferentes solicitudes.
- Facilita el bloqueo del usuario después de tres intentos fallidos.
- Proporciona un único punto de acceso al administrador de intentos.
- Simplifica la gestión temporal del estado de autenticación.

---

## 10. Consideraciones y limitaciones

Actualmente los intentos fallidos son almacenados en memoria.

Esto significa que si el microservicio `identity-service` se reinicia, la información almacenada por `LoginAttemptManager` también se reinicia.

Además, Singleton garantiza una única instancia dentro de un proceso de ejecución.

Si en el futuro AgroPrecision ejecutara varias instancias del microservicio `identity-service`, cada proceso tendría su propio Singleton.

Para una arquitectura distribuida, el almacenamiento de intentos podría evolucionar hacia soluciones como:

- PostgreSQL.
- Redis.
- Un servicio centralizado de autenticación.

---

## 11. Evidencia en video

La implementación y funcionamiento del patrón Singleton se demuestra mediante un video ubicado en:

`Semana 3 (Singleton)/video/Singleton_AgroPrecision_Semana3.mov`

En el video se presenta:

- La clase `LoginAttemptManager`.
- La instancia estática.
- El constructor privado.
- El método `getInstance()`.
- La utilización del Singleton desde `AuthService`.
- El incremento de los intentos fallidos.
- El bloqueo después de tres intentos.

---

## 12. Diagrama UML

El diagrama UML correspondiente a la implementación se encuentra en:

`Semana 3 (Singleton)/uml/Singleton_AgroPrecision.png`

El diagrama representa la relación entre `AuthService` y `LoginAttemptManager`, así como los principales atributos y métodos utilizados para implementar el patrón Singleton.

---

## 13. Conclusión

El patrón Singleton fue implementado exitosamente dentro del módulo de autenticación de AgroPrecision.

La clase `LoginAttemptManager` mantiene una única instancia responsable de administrar los intentos fallidos de inicio de sesión.

Mediante esta implementación, diferentes solicitudes de autenticación comparten el mismo estado, permitiendo contabilizar los intentos realizados y bloquear al usuario después de tres intentos incorrectos.

La implementación fue validada tanto mediante una prueba automatizada con JUnit como mediante una demostración funcional desde la interfaz de AgroPrecision.

De esta manera, el patrón Singleton aporta una solución centralizada y controlada para la gestión temporal de intentos de autenticación dentro del microservicio de identidad.