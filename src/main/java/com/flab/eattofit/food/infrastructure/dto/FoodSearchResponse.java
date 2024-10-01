package com.flab.eattofit.food.infrastructure.dto;

import java.math.BigDecimal;

public record FoodSearchResponse(
        String name,
        BigDecimal servingSize,
        String unit,
        BigDecimal kcal,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        BigDecimal sodium
) {
}
