package com.agroprecision.irrigation.application.builder;

import com.agroprecision.irrigation.domain.model.IrrigationPlan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AutomaticIrrigationPlanBuilderTest {

    @Test
    void shouldBuildAutomaticIrrigationPlan() {

        IrrigationPlanBuilder builder =
                new AutomaticIrrigationPlanBuilder();

        IrrigationPlan plan = builder
                .cropId("CROP-01")
                .zone("A1")
                .soilMoistureThreshold(35.0)
                .durationMinutes(25)
                .waterVolumeLiters(180.0)
                .automatic(true)
                .priority("HIGH")
                .build();

        assertEquals("CROP-01", plan.getCropId());
        assertEquals("A1", plan.getZone());
        assertEquals(35.0, plan.getSoilMoistureThreshold());
        assertEquals(25, plan.getDurationMinutes());
        assertEquals(180.0, plan.getWaterVolumeLiters());
        assertTrue(plan.isAutomatic());
        assertEquals("HIGH", plan.getPriority());
    }
}