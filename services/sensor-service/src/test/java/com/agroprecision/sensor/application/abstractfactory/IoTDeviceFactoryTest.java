package com.agroprecision.sensor.application.abstractfactory;

import com.agroprecision.sensor.domain.device.LoRaGateway;
import com.agroprecision.sensor.domain.device.LoRaSensor;
import com.agroprecision.sensor.domain.device.WiFiGateway;
import com.agroprecision.sensor.domain.device.WiFiSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class IoTDeviceFactoryTest {

    @Test
    void shouldCreateLoRaDeviceFamily() {

        IoTDeviceFactory factory =
                new LoRaDeviceFactory();

        var sensor =
                factory.createSensor();

        var gateway =
                factory.createGateway();

        assertInstanceOf(
                LoRaSensor.class,
                sensor
        );

        assertInstanceOf(
                LoRaGateway.class,
                gateway
        );

        assertEquals(
                sensor.getTechnology(),
                gateway.getTechnology()
        );
    }

    @Test
    void shouldCreateWiFiDeviceFamily() {

        IoTDeviceFactory factory =
                new WiFiDeviceFactory();

        var sensor =
                factory.createSensor();

        var gateway =
                factory.createGateway();

        assertInstanceOf(
                WiFiSensor.class,
                sensor
        );

        assertInstanceOf(
                WiFiGateway.class,
                gateway
        );

        assertEquals(
                sensor.getTechnology(),
                gateway.getTechnology()
        );
    }
}