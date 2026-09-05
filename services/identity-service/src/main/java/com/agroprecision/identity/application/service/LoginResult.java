package com.agroprecision.identity.application.service;

public record LoginResult(
        boolean success,
        boolean blocked,
        String message,
        int failedAttempts
) {
}