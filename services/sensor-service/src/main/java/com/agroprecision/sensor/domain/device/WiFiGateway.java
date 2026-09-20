package com.agroprecision.sensor.domain.device;

public class WiFiGateway implements Gateway {

    @Override
    public String getTechnology() {
        return "WiFi";
    }

    @Override
    public String getDescription() {
        return "Gateway WiFi para centralizar datos de sensores agrícolas.";
    }
}