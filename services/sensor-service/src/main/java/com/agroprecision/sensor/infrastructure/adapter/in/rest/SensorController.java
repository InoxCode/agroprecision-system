package com.agroprecision.sensor.infrastructure.adapter.in.rest;

import com.agroprecision.sensor.application.factory.AirHumiditySensorCreator;
import com.agroprecision.sensor.application.factory.SensorCreator;
import com.agroprecision.sensor.application.factory.SoilMoistureSensorCreator;
import com.agroprecision.sensor.application.factory.TemperatureSensorCreator;
import com.agroprecision.sensor.domain.model.Sensor;
import com.agroprecision.sensor.infrastructure.adapter.in.rest.dto.SensorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @GetMapping("/{type}")
    public ResponseEntity<SensorResponse> createSensor(
            @PathVariable String type
    ) {

        SensorCreator creator = getCreator(type);

        Sensor sensor = creator.createSensor();

        SensorResponse response = new SensorResponse(
                sensor.getType(),
                sensor.getUnit(),
                sensor.getDescription()
        );

        return ResponseEntity.ok(response);
    }

    private SensorCreator getCreator(String type) {

        return switch (type.toLowerCase()) {

            case "temperature" ->
                    new TemperatureSensorCreator();

            case "soil-moisture" ->
                    new SoilMoistureSensorCreator();

            case "air-humidity" ->
                    new AirHumiditySensorCreator();

            default ->
                    throw new IllegalArgumentException(
                            "Tipo de sensor no soportado: " + type
                    );
        };
    }
}