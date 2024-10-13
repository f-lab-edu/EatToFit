package com.flab.eattofit.plan.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PlanSearchRequest(
        @NotNull(message = "검색할 플랜의 칼로리가 있어야 합니다.")
        @DecimalMin(value = "0.0", inclusive = false, message = "칼로리는 0보다 커야 합니다.")
        BigDecimal kcal
) {
}
