package com.agroprecision.irrigation.application.builder;

import com.agroprecision.irrigation.domain.model.IrrigationPlan;

public interface IrrigationPlanBuilder {

    IrrigationPlanBuilder cropId(String cropId);

    IrrigationPlanBuilder zone(String zone);

    IrrigationPlanBuilder soilMoistureThreshold(
            double soilMoistureThreshold
    );

    IrrigationPlanBuilder durationMinutes(
            int durationMinutes
    );

    IrrigationPlanBuilder waterVolumeLiters(
            double waterVolumeLiters
    );

    IrrigationPlanBuilder automatic(
            boolean automatic
    );

    IrrigationPlanBuilder priority(
            String priority
    );

    IrrigationPlan build();
}