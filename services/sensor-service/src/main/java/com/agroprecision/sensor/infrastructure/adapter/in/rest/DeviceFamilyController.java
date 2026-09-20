package com.agroprecision.sensor.infrastructure.adapter.in.rest;

import com.agroprecision.sensor.application.abstractfactory.IoTDeviceFactory;
import com.agroprecision.sensor.application.abstractfactory.LoRaDeviceFactory;
import com.agroprecision.sensor.application.abstractfactory.WiFiDeviceFactory;
import com.agroprecision.sensor.domain.device.Gateway;
import com.agroprecision.sensor.domain.device.IoTSensor;
import com.agroprecision.sensor.infrastructure.adapter.in.rest.dto.DeviceFamilyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device-families")
public class DeviceFamilyController {

    @GetMapping("/{technology}")
    public ResponseEntity<DeviceFamilyResponse> createFamily(
            @PathVariable String technology
    ) {

        IoTDeviceFactory factory =
                getFactory(technology);

        IoTSensor sensor =
                factory.createSensor();

        Gateway gateway =
                factory.createGateway();

        DeviceFamilyResponse response =
                new DeviceFamilyResponse(
                        sensor.getTechnology(),
                        sensor.getDescription(),
                        gateway.getDescription()
                );

        return ResponseEntity.ok(response);
    }

    private IoTDeviceFactory getFactory(
            String technology
    ) {

        return switch (technology.toLowerCase()) {

            case "lora" ->
                    new LoRaDeviceFactory();

            case "wifi" ->
                    new WiFiDeviceFactory();

            default ->
                    throw new IllegalArgumentException(
                            "Tecnología no soportada: "
                                    + technology
                    );
        };
    }
}