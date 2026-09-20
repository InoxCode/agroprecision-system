package com.agroprecision.irrigation.domain.model;

import com.agroprecision.irrigation.prototype.Prototype;

public class IrrigationPlan
        implements Prototype<IrrigationPlan> {

    private final String cropId;
    private final String zone;
    private final double soilMoistureThreshold;
    private final int durationMinutes;
    private final double waterVolumeLiters;
    private final boolean automatic;
    private final String priority;

    public IrrigationPlan(
            String cropId,
            String zone,
            double soilMoistureThreshold,
            int durationMinutes,
            double waterVolumeLiters,
            boolean automatic,
            String priority
    ) {
        this.cropId = cropId;
        this.zone = zone;
        this.soilMoistureThreshold = soilMoistureThreshold;
        this.durationMinutes = durationMinutes;
        this.waterVolumeLiters = waterVolumeLiters;
        this.automatic = automatic;
        this.priority = priority;
    }

    @Override
    public IrrigationPlan copy() {
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

    public IrrigationPlan copyFor(
            String newCropId,
            String newZone
    ) {
        return new IrrigationPlan(
                newCropId,
                newZone,
                soilMoistureThreshold,
                durationMinutes,
                waterVolumeLiters,
                automatic,
                priority
        );
    }

    public String getCropId() {
        return cropId;
    }

    public String getZone() {
        return zone;
    }

    public double getSoilMoistureThreshold() {
        return soilMoistureThreshold;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getWaterVolumeLiters() {
        return waterVolumeLiters;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public String getPriority() {
        return priority;
    }
}