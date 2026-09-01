import { useState, type FormEvent } from "react";
import "./App.css";

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    console.log("Correo:", email);
    console.log("Contraseña:", password);
  };

  return (
    <main className="login-page">
      <section className="login-container">
        <div className="login-brand">
          <div className="logo">AP</div>

          <h1>AgroPrecision</h1>

          <p>Sistema de Agricultura de Precisión</p>
        </div>

        <form className="login-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Correo electrónico</label>

            <input
              id="email"
              type="email"
              placeholder="usuario@agroprecision.com"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Contraseña</label>

            <div className="password-container">
              <input
                id="password"
                type={showPassword ? "text" : "password"}
                placeholder="Ingrese su contraseña"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                required
              />

              <button
                type="button"
                className="show-password"
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? "Ocultar" : "Ver"}
              </button>
            </div>
          </div>

          <button className="login-button" type="submit">
            Iniciar sesión
          </button>
        </form>

        <div className="login-footer">
          <p>Plataforma de gestión agrícola inteligente</p>
        </div>
      </section>

      <section className="information-panel">
        <div className="information-content">
          <p className="information-label">AGRICULTURA DE PRECISIÓN</p>

          <h2>
            Tecnología para tomar mejores decisiones sobre tus cultivos.
          </h2>

          <p>
            <p>
  Monitorea tus cultivos mediante drones y sensores IoT, automatiza el riego,
  gestiona inventarios y cadena de frío, y analiza datos históricos para apoyar
  la predicción de cosechas desde una plataforma centralizada.
</p>
          </p>

          <div className="features">
  <div>
    <strong>Monitoreo</strong>
    <span>Drones, sensores IoT y estado de cultivos</span>
  </div>

  <div>
    <strong>Riego</strong>
    <span>Automatización basada en clima y datos del suelo</span>
  </div>

  <div>
    <strong>Inventario y cadena de frío</strong>
    <span>Control de productos, existencias y temperatura</span>
  </div>

  <div>
    <strong>Producción</strong>
    <span>Datos históricos y predicción de cosechas</span>
  </div>
</div>
        </div>
      </section>
    </main>
  );
}

export default App;