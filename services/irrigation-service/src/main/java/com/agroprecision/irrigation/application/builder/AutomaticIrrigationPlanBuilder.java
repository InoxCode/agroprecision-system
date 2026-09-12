package com.agroprecision.irrigation.application.builder;

import com.agroprecision.irrigation.domain.model.IrrigationPlan;

public class AutomaticIrrigationPlanBuilder
        implements IrrigationPlanBuilder {

    private String cropId;
    private String zone;
    private double soilMoistureThreshold;
    private int durationMinutes;
    private double waterVolumeLiters;
    private boolean automatic;
    private String priority;

    @Override
    public IrrigationPlanBuilder cropId(String cropId) {
        this.cropId = cropId;
        return this;
    }

    @Override
    public IrrigationPlanBuilder zone(String zone) {
        this.zone = zone;
        return this;
    }

    @Override
    public IrrigationPlanBuilder soilMoistureThreshold(
            double soilMoistureThreshold
    ) {
        this.soilMoistureThreshold = soilMoistureThreshold;
        return this;
    }

    @Override
    public IrrigationPlanBuilder durationMinutes(
            int durationMinutes
    ) {
        this.durationMinutes = durationMinutes;
        return this;
    }

    @Override
    public IrrigationPlanBuilder waterVolumeLiters(
            double waterVolumeLiters
    ) {
        this.waterVolumeLiters = waterVolumeLiters;
        return this;
    }

    @Override
    public IrrigationPlanBuilder automatic(
            boolean automatic
    ) {
        this.automatic = automatic;
        return this;
    }

    @Override
    public IrrigationPlanBuilder priority(String priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public IrrigationPlan build() {
        return new IrrigationPlan(
                cropId,
                zone,
                soilMoistureThreshold,
                durationMinutes,
                waterVolumeLiters,
                automatic,
                priority
        );
    }
}