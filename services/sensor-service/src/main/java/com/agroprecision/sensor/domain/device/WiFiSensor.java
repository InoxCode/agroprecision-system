package com.agroprecision.sensor.domain.device;

public class WiFiSensor implements IoTSensor {

    @Override
    public String getTechnology() {
        return "WiFi";
    }

    @Override
    public String getDescription() {
        return "Sensor IoT WiFi para monitoreo agrícola mediante red inalámbrica.";
    }
}