package com.agroprecision.irrigation.application.director;

import com.agroprecision.irrigation.application.builder.AutomaticIrrigationPlanBuilder;
import com.agroprecision.irrigation.application.builder.IrrigationPlanBuilder;
import com.agroprecision.irrigation.domain.model.IrrigationPlan;
import org.springframework.stereotype.Service;

@Service
public class IrrigationPlanDirector {

    public IrrigationPlan createAutomaticPlan(
            String cropId,
            String zone
    ) {

        IrrigationPlanBuilder builder =
                new AutomaticIrrigationPlanBuilder();

        return builder
                .cropId(cropId)
                .zone(zone)
                .soilMoistureThreshold(35.0)
                .durationMinutes(25)
                .waterVolumeLiters(180.0)
                .automatic(true)
                .priority("HIGH")
                .build();
    }
}