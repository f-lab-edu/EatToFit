package com.flab.eattofit.plan.infrastructure.dto;

import java.math.BigDecimal;

public record SportsSearchResponse(
        String name,
        BigDecimal expect,
        Integer time
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
