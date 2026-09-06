package com.agroprecision.sensor.application.factory;

import com.agroprecision.sensor.domain.model.Sensor;
import com.agroprecision.sensor.domain.model.SoilMoistureSensor;

public class SoilMoistureSensorCreator extends SensorCreator {

    @Override
    public Sensor createSensor() {
        return new SoilMoistureSensor();
    }
}