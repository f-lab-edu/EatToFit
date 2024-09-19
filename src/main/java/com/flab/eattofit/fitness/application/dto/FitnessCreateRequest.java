package com.flab.eattofit.fitness.application.dto;

import jakarta.validation.constraints.NotEmpty;

public record FitnessCreateRequest(
        @NotEmpty(message = "등록할 피트니스 이름이 필요합니다.")
        String name
) {
}
