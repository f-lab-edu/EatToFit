package com.flab.eattofit.food.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FoodCreateRequest(
        @NotEmpty(message = "음식 이름이 있어야 합니다.")
        String name,

        @NotNull(message = "음식 사이즈가 있어야 합니다.")
        @DecimalMin(value = "0", inclusive = false, message = "음식 사이즈는 0보다 커야 합니다.")
        BigDecimal servingSize,

        @NotEmpty(message = "음식 사이즈 단위가 있어야 합니다.")
        String unit,

        @NotNull(message = "음식 칼로리가 있어야 합니다.")
        @DecimalMin(value = "0", message = "음식 칼로리는 0 이상이어야 합니다.")
        BigDecimal kcal,

        @NotNull(message = "음식 탄수화물이 있어야 합니다.")
        @DecimalMin(value = "0", message = "음식 탄수화물은 0 이상이어야 합니다.")
        BigDecimal carbohydrate,

        @NotNull(message = "음식 단백질이 있어야 합니다.")
        @DecimalMin(value = "0", message = "음식 단백질은 0 이상이어야 합니다.")
        BigDecimal protein,

        @NotNull(message = "음식 지방이 있어야 합니다.")
        @DecimalMin(value = "0", message = "음식 지방은 0 이상이어야 합니다.")
        BigDecimal fat,

        @NotNull(message = "음식 나트륨이 있어야 합니다.")
        @DecimalMin(value = "0", message = "음식 나트륨은 0 이상이어야 합니다.")
        BigDecimal sodium
) {
}
