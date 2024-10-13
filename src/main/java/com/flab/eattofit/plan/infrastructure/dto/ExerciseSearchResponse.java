package com.flab.eattofit.plan.infrastructure.dto;

import java.math.BigDecimal;

public record ExerciseSearchResponse(
        String name,
        Integer repeat,
        BigDecimal expect,
        Integer size,
        BigDecimal weight,
        Integer time
) {
}
