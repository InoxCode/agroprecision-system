package com.agroprecision.sensor.domain.model;

public class AirHumiditySensor implements Sensor {

    @Override
    public String getType() {
        return "Humedad ambiental";
    }

    @Override
    public String getUnit() {
        return "%";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT para monitorear la humedad relativa del ambiente.";
    }
}