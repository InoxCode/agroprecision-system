package com.agroprecision.sensor.application.factory;

import com.agroprecision.sensor.domain.model.Sensor;

public abstract class SensorCreator {

    public abstract Sensor createSensor();
}