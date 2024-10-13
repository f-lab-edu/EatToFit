package com.flab.eattofit.plan.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ExerciseCreateRequest(
        @NotEmpty(message = "등록할 운동 이름이 필요합니다.")
        String name,

        Integer repeat,

        @NotNull(message = "등록할 운동의 예상 소모 칼로리가 필요합니다.")
        @DecimalMin(value = "0", inclusive = false, message = "칼로리는 0보다 커야 합니다.")
        BigDecimal expect,

        Integer size,

        BigDecimal weight,

        Integer time
) {
}
