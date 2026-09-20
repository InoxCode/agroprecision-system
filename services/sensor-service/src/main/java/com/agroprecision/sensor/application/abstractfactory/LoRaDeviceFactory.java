package com.agroprecision.sensor.application.abstractfactory;

import com.agroprecision.sensor.domain.device.Gateway;
import com.agroprecision.sensor.domain.device.IoTSensor;
import com.agroprecision.sensor.domain.device.LoRaGateway;
import com.agroprecision.sensor.domain.device.LoRaSensor;

public class LoRaDeviceFactory implements IoTDeviceFactory {

    @Override
    public IoTSensor createSensor() {
        return new LoRaSensor();
    }

    @Override
    public Gateway createGateway() {
        return new LoRaGateway();
    }
}