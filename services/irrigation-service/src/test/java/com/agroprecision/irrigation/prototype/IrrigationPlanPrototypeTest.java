package com.agroprecision.irrigation.prototype;

import com.agroprecision.irrigation.domain.model.IrrigationPlan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class IrrigationPlanPrototypeTest {

    @Test
    void shouldCreateIndependentCopy() {

        IrrigationPlan original =
                new IrrigationPlan(
                        "CROP-01",
                        "A1",
                        35.0,
                        25,
                        180.0,
                        true,
                        "HIGH"
                );

        IrrigationPlan copy =
                original.copy();

        assertNotSame(
                original,
                copy
        );

        assertEquals(
                original.getCropId(),
                copy.getCropId()
        );

        assertEquals(
                original.getZone(),
                copy.getZone()
        );

        assertEquals(
                original.getSoilMoistureThreshold(),
                copy.getSoilMoistureThreshold()
        );

        assertEquals(
                original.getDurationMinutes(),
                copy.getDurationMinutes()
        );

        assertEquals(
                original.getWaterVolumeLiters(),
                copy.getWaterVolumeLiters()
        );

        assertEquals(
                original.getPriority(),
                copy.getPriority()
        );
    }

    @Test
    void shouldCloneConfigurationForAnotherZone() {

        IrrigationPlan prototype =
                new IrrigationPlan(
                        "CROP-01",
                        "A1",
                        35.0,
                        25,
                        180.0,
                        true,
                        "HIGH"
                );

        IrrigationPlan clone =
                prototype.copyFor(
                        "CROP-02",
                        "B1"
                );

        assertNotSame(
                prototype,
                clone
        );

        assertEquals(
                "CROP-02",
                clone.getCropId()
        );

        assertEquals(
                "B1",
                clone.getZone()
        );

        assertEquals(
                prototype.getSoilMoistureThreshold(),
                clone.getSoilMoistureThreshold()
        );

        assertEquals(
                prototype.getDurationMinutes(),
                clone.getDurationMinutes()
        );

        assertEquals(
                prototype.getWaterVolumeLiters(),
                clone.getWaterVolumeLiters()
        );

        assertEquals(
                prototype.getPriority(),
                clone.getPriority()
        );
    }
}