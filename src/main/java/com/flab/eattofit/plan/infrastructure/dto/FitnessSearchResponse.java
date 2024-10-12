package com.flab.eattofit.plan.infrastructure.dto;

import java.math.BigDecimal;

public record FitnessSearchResponse(
        String name,
        Integer repeat,
        BigDecimal expect,
        Integer size,
        BigDecimal weight
) implements ExerciseSearchResponse {

    @Override
    public String name() {
        return name;
    }

    @Override
    public BigDecimal expect() {
        return expect;
    }
}
