package com.agroprecision.sensor.domain.device;

public class LoRaSensor implements IoTSensor {

    @Override
    public String getTechnology() {
        return "LoRa";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT LoRa para monitoreo agrícola de largo alcance.";
    }
}