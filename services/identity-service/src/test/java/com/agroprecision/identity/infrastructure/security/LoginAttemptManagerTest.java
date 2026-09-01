package com.agroprecision.identity.infrastructure.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class LoginAttemptManagerTest {

    @Test
    void shouldReturnTheSameInstance() {

        LoginAttemptManager first =
                LoginAttemptManager.getInstance();

        LoginAttemptManager second =
                LoginAttemptManager.getInstance();

        assertSame(first, second);
    }
}