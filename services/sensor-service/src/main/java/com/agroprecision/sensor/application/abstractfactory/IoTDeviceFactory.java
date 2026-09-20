package com.agroprecision.sensor.application.abstractfactory;

import com.agroprecision.sensor.domain.device.Gateway;
import com.agroprecision.sensor.domain.device.IoTSensor;

public interface IoTDeviceFactory {

    IoTSensor createSensor();

    Gateway createGateway();
}