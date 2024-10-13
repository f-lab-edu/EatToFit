package com.flab.eattofit.plan.infrastructure.dto;

import java.util.List;

public record PredictPlanSearchResponse(
        List<PlanSearchResponse> plans
) {
}
