package com.agroprecision.sensor.infrastructure.adapter.in.rest.dto;

public record SensorResponse(
        String type,
        String unit,
        String description
) {
}