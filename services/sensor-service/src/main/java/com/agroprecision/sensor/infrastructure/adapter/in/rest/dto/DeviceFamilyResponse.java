package com.agroprecision.sensor.infrastructure.adapter.in.rest.dto;

public record DeviceFamilyResponse(
        String technology,
        String sensor,
        String gateway
) {
}