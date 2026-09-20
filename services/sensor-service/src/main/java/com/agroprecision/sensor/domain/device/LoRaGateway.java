package com.agroprecision.sensor.domain.device;

public class LoRaGateway implements Gateway {

    @Override
    public String getTechnology() {
        return "LoRa";
    }

    @Override
    public String getDescription() {
        return "Gateway LoRa para recibir datos de sensores agrícolas.";
    }
}