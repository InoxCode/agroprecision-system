import { useState, type FormEvent } from "react";
import { login } from "./services/authService";
import "./App.css";

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const [message, setMessage] = useState("");
  const [failedAttempts, setFailedAttempts] = useState(0);
  const [blocked, setBlocked] = useState(false);
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (
    event: FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();

    setLoading(true);
    setMessage("");

    try {
      const result = await login(email, password);

      setMessage(result.message);
      setFailedAttempts(result.failedAttempts);
      setBlocked(result.blocked);
      setSuccess(result.success);
    } catch {
      setMessage(
        "No fue posible conectar con el servidor de AgroPrecision."
      );

      setSuccess(false);
    } finally {
      setLoading(false);
    }
  };

  if (success) {
    return (
      <main className="login-page">
        <section className="login-container">
          <div className="login-brand">
            <div className="logo">AP</div>

            <h1>AgroPrecision</h1>

            <p>Sistema de Agricultura de Precisión</p>
          </div>

          <div className="welcome-panel">
            <h2>Inicio de sesión exitoso</h2>

            <p>
              Bienvenido al Sistema de Agricultura de Precisión.
            </p>

            <p>
              Usuario: <strong>{email}</strong>
            </p>

            <button
              className="login-button"
              onClick={() => {
                setSuccess(false);
                setPassword("");
                setMessage("");
                setFailedAttempts(0);
              }}
            >
              Cerrar sesión
            </button>
          </div>
        </section>

        <section className="information-panel">
          <div className="information-content">
            <p className="information-label">
              AGROPRECISION
            </p>

            <h2>
              Acceso autorizado correctamente.
            </h2>

            <p>
              El servicio de identidad validó las credenciales
              del usuario correctamente.
            </p>
          </div>
        </section>
      </main>
    );
  }

  return (
    <main className="login-page">
      <section className="login-container">
        <div className="login-brand">
          <div className="logo">AP</div>

          <h1>AgroPrecision</h1>

          <p>Sistema de Agricultura de Precisión</p>
        </div>

        <form
          className="login-form"
          onSubmit={handleSubmit}
        >
          <div className="form-group">
            <label htmlFor="email">
              Correo electrónico
            </label>

            <input
              id="email"
              type="email"
              placeholder="usuario@agroprecision.com"
              value={email}
              onChange={(event) =>
                setEmail(event.target.value)
              }
              disabled={blocked}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">
              Contraseña
            </label>

            <div className="password-container">
              <input
                id="password"
                type={showPassword ? "text" : "password"}
                placeholder="Ingrese su contraseña"
                value={password}
                onChange={(event) =>
                  setPassword(event.target.value)
                }
                disabled={blocked}
                required
              />

              <button
                type="button"
                className="show-password"
                onClick={() =>
                  setShowPassword(!showPassword)
                }
                disabled={blocked}
              >
                {showPassword ? "Ocultar" : "Ver"}
              </button>
            </div>
          </div>

          <button
            className="login-button"
            type="submit"
            disabled={loading || blocked}
          >
            {loading
              ? "Validando..."
              : blocked
                ? "Usuario bloqueado"
                : "Iniciar sesión"}
          </button>

          {message && (
            <div
              className={
                blocked
                  ? "login-message blocked-message"
                  : "login-message"
              }
            >
              <p>{message}</p>

              {!blocked && failedAttempts > 0 && (
                <span>
                  Intentos fallidos: {failedAttempts} de 3
                </span>
              )}

              {blocked && (
                <span>
                  Se alcanzó el límite de intentos permitidos.
                </span>
              )}
            </div>
          )}
        </form>

        <div className="login-footer">
          <p>
            Plataforma de gestión agrícola inteligente
          </p>
        </div>
      </section>

      <section className="information-panel">
        <div className="information-content">
          <p className="information-label">
            AGRICULTURA DE PRECISIÓN
          </p>

          <h2>
            Tecnología para tomar mejores decisiones
            sobre tus cultivos.
          </h2>

          <p>
            Monitorea tus cultivos mediante drones y sensores IoT,
            automatiza el riego, gestiona inventarios y cadena de
            frío, y analiza datos históricos para apoyar la
            predicción de cosechas desde una plataforma
            centralizada.
          </p>

          <div className="features">
            <div>
              <strong>Monitoreo</strong>
              <span>
                Drones, sensores IoT y estado de cultivos
              </span>
            </div>

            <div>
              <strong>Riego</strong>
              <span>
                Automatización basada en clima y datos del suelo
              </span>
            </div>

            <div>
              <strong>
                Inventario y cadena de frío
              </strong>
              <span>
                Control de productos, existencias y temperatura
              </span>
            </div>

            <div>
              <strong>Producción</strong>
              <span>
                Datos históricos y predicción de cosechas
              </span>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}

export default App;