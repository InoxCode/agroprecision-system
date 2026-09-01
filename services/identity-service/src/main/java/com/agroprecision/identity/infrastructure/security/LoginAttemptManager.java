package com.agroprecision.identity.infrastructure.security;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class LoginAttemptManager {

    private static final int MAX_ATTEMPTS = 3;

    private static final LoginAttemptManager INSTANCE =
            new LoginAttemptManager();

    private final ConcurrentMap<String, Integer> attempts;

    private LoginAttemptManager() {
        this.attempts = new ConcurrentHashMap<>();
    }

    public static LoginAttemptManager getInstance() {
        return INSTANCE;
    }

    public void registerFailedAttempt(String email) {
        attempts.merge(email, 1, Integer::sum);
    }

    public void registerSuccessfulLogin(String email) {
        attempts.remove(email);
    }

    public int getFailedAttempts(String email) {
        return attempts.getOrDefault(email, 0);
    }

    public boolean isBlocked(String email) {
        return getFailedAttempts(email) >= MAX_ATTEMPTS;
    }
}