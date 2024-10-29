package com.flab.eattofit.food.domain.dto;

import java.math.BigDecimal;

public record FoodNutrientCreateDto(
        BigDecimal kcal,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        BigDecimal sodium
) {

    public static FoodNutrientCreateDto from(final FoodCreateDto foodCreateDto) {
        return new FoodNutrientCreateDto(
                foodCreateDto.kcal(),
                foodCreateDto.carbohydrate(),
                foodCreateDto.protein(),
                foodCreateDto.fat(),
                foodCreateDto.sodium()
        );
    }
}
