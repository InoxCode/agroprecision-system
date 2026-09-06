package com.agroprecision.sensor.application.factory;

import com.agroprecision.sensor.domain.model.Sensor;
import com.agroprecision.sensor.domain.model.TemperatureSensor;

public class TemperatureSensorCreator extends SensorCreator {

    @Override
    public Sensor createSensor() {
        return new TemperatureSensor();
    }
}