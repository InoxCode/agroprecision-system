package com.agroprecision.identity.application.service;

import com.agroprecision.identity.application.port.in.LoginUseCase;
import com.agroprecision.identity.application.port.out.UserCredentialPort;
import com.agroprecision.identity.infrastructure.security.LoginAttemptManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements LoginUseCase {

    private final UserCredentialPort userCredentialPort;
    private final LoginAttemptManager attemptManager;

    public AuthService(UserCredentialPort userCredentialPort) {

        this.userCredentialPort = userCredentialPort;

        this.attemptManager =
                LoginAttemptManager.getInstance();
    }

    @Override
    public LoginResult login(String email, String password) {

        String normalizedEmail =
                email.trim().toLowerCase();

        if (attemptManager.isBlocked(normalizedEmail)) {

            return new LoginResult(
                    false,
                    true,
                    "Usuario bloqueado por demasiados intentos fallidos.",
                    attemptManager.getFailedAttempts(normalizedEmail)
            );
        }

        boolean valid =
                userCredentialPort.credentialsAreValid(
                        normalizedEmail,
                        password
                );

        if (valid) {

            attemptManager.registerSuccessfulLogin(
                    normalizedEmail
            );

            return new LoginResult(
                    true,
                    false,
                    "Inicio de sesión exitoso.",
                    0
            );
        }

        attemptManager.registerFailedAttempt(
                normalizedEmail
        );

        int failedAttempts =
                attemptManager.getFailedAttempts(
                        normalizedEmail
                );

        boolean blocked =
                attemptManager.isBlocked(
                        normalizedEmail
                );

        if (blocked) {

            return new LoginResult(
                    false,
                    true,
                    "Usuario bloqueado después de 3 intentos fallidos.",
                    failedAttempts
            );
        }

        return new LoginResult(
                false,
                false,
                "Correo electrónico o contraseña incorrectos.",
                failedAttempts
        );
    }
}