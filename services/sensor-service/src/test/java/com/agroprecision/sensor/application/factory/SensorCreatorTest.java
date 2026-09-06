package com.agroprecision.sensor.application.factory;

import com.agroprecision.sensor.domain.model.AirHumiditySensor;
import com.agroprecision.sensor.domain.model.Sensor;
import com.agroprecision.sensor.domain.model.SoilMoistureSensor;
import com.agroprecision.sensor.domain.model.TemperatureSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class SensorCreatorTest {

    @Test
    void shouldCreateTemperatureSensor() {

        SensorCreator creator =
                new TemperatureSensorCreator();

        Sensor sensor =
                creator.createSensor();

        assertInstanceOf(TemperatureSensor.class, sensor);
        assertEquals("Temperatura", sensor.getType());
        assertEquals("°C", sensor.getUnit());
    }

    @Test
    void shouldCreateSoilMoistureSensor() {

        SensorCreator creator =
                new SoilMoistureSensorCreator();

        Sensor sensor =
                creator.createSensor();

        assertInstanceOf(SoilMoistureSensor.class, sensor);
        assertEquals("Humedad del suelo", sensor.getType());
        assertEquals("%", sensor.getUnit());
    }

    @Test
    void shouldCreateAirHumiditySensor() {

        SensorCreator creator =
                new AirHumiditySensorCreator();

        Sensor sensor =
                creator.createSensor();

        assertInstanceOf(AirHumiditySensor.class, sensor);
        assertEquals("Humedad ambiental", sensor.getType());
        assertEquals("%", sensor.getUnit());
    }
}