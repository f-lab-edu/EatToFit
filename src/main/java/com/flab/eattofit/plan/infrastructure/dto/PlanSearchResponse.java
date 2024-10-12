package com.flab.eattofit.plan.infrastructure.dto;

import java.util.List;

public record PlanSearchResponse(
        String type,
        List<? extends ExerciseSearchResponse> exercises
) {
}
