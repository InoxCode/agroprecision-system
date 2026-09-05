package com.agroprecision.identity.infrastructure.adapter.out.persistence;

import com.agroprecision.identity.application.port.out.UserCredentialPort;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserCredentialAdapter implements UserCredentialPort {

    private static final String DEMO_EMAIL =
            "admin@agroprecision.com";

    private static final String DEMO_PASSWORD =
            "Agro2026!";

    @Override
    public boolean credentialsAreValid(String email, String password) {

        return DEMO_EMAIL.equalsIgnoreCase(email)
                && DEMO_PASSWORD.equals(password);
    }
}