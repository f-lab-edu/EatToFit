package com.flab.eattofit.fitness.application.memberfitness.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MemberFitnessesRequest(
        @NotNull(message = "선호할 피트니스 id 목록이 있어야 합니다.")
        List<Long> fitnesses
) {
}
