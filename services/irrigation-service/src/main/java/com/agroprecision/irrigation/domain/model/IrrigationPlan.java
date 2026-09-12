package com.agroprecision.irrigation.domain.model;

public class IrrigationPlan {

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