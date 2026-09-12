package com.agroprecision.irrigation.infrastructure.adapter.in.rest;

import com.agroprecision.irrigation.application.director.IrrigationPlanDirector;
import com.agroprecision.irrigation.domain.model.IrrigationPlan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/irrigation")
public class IrrigationController {

    private final IrrigationPlanDirector director;

    public IrrigationController(
            IrrigationPlanDirector director
    ) {
        this.director = director;
    }

    @GetMapping("/automatic-plan")
    public IrrigationPlan createAutomaticPlan(
            @RequestParam String cropId,
            @RequestParam String zone
    ) {
        return director.createAutomaticPlan(
                cropId,
                zone
        );
    }
}