package com.agroprecision.sensor.application.factory;

import com.agroprecision.sensor.domain.model.AirHumiditySensor;
import com.agroprecision.sensor.domain.model.Sensor;

public class AirHumiditySensorCreator extends SensorCreator {

    @Override
    public Sensor createSensor() {
        return new AirHumiditySensor();
    }
}