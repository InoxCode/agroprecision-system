package com.agroprecision.sensor.domain.model;

public class SoilMoistureSensor implements Sensor {

    @Override
    public String getType() {
        return "Humedad del suelo";
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT para monitorear la humedad presente en el suelo.";
    }
}