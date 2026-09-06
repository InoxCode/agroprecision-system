package com.agroprecision.sensor.domain.model;

public class TemperatureSensor implements Sensor {

    @Override
    public String getType() {
        return "Temperatura";
    }

    @Override
    public String getUnit() {
        return "°C";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT para monitorear la temperatura del cultivo.";
    }
}