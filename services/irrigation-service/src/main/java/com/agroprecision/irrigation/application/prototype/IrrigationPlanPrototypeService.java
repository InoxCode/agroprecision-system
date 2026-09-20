package com.agroprecision.irrigation.application.prototype;

import com.agroprecision.irrigation.application.director.IrrigationPlanDirector;
import com.agroprecision.irrigation.domain.model.IrrigationPlan;
import org.springframework.stereotype.Service;

@Service
public class IrrigationPlanPrototypeService {

    private final IrrigationPlan prototype;

    public IrrigationPlanPrototypeService(
            IrrigationPlanDirector director
    ) {
        this.prototype =
                director.createAutomaticPlan(
                        "CROP-TEMPLATE",
                        "TEMPLATE"
                );
    }

    public IrrigationPlan clonePlan(
            String cropId,
            String zone
    ) {
        return prototype.copyFor(
                cropId,
                zone
        );
    }

    public IrrigationPlan getPrototypeCopy() {
        return prototype.copy();
    }
}