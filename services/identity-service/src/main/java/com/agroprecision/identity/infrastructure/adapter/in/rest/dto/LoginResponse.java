package com.agroprecision.identity.infrastructure.adapter.in.rest.dto;

public record LoginResponse(
        boolean success,
        boolean blocked,
        String message,
        int failedAttempts
) {
}