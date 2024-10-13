package com.flab.eattofit.plan.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PlanCreateRequest(
        @NotEmpty(message = "플랜 타입이 필요합니다.")
        String type,

        @Valid
        @NotNull(message = "플랜의 운동이 필요합니다.")
        List<ExerciseCreateRequest> exercises
) {
}
