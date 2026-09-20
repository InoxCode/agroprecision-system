package com.agroprecision.sensor.application.abstractfactory;

import com.agroprecision.sensor.domain.device.Gateway;
import com.agroprecision.sensor.domain.device.IoTSensor;
import com.agroprecision.sensor.domain.device.WiFiGateway;
import com.agroprecision.sensor.domain.device.WiFiSensor;

public class WiFiDeviceFactory implements IoTDeviceFactory {

    @Override
    public IoTSensor createSensor() {
        return new WiFiSensor();
    }

    @Override
    public Gateway createGateway() {
        return new WiFiGateway();
    }
}