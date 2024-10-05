package com.flab.eattofit.food.ui.dto;

import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FoodCreateResponse(
        String name,
        BigDecimal servingSize,
        String unit,
        BigDecimal kcal,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        BigDecimal sodium,
        Long memberId,
        LocalDateTime createdAt
) {

    public static FoodCreateResponse from(final Food food) {
        FoodWeight weight = food.getWeight();
        FoodNutrient nutrient = food.getNutrient();

        return new FoodCreateResponse(
                food.getName(),
                weight.getServingSize(),
                weight.getUnit().getName(),
                nutrient.getKcal(),
                nutrient.getCarbohydrate(),
                nutrient.getProtein(),
                nutrient.getFat(),
                nutrient.getSodium(),
                food.getMemberId(),
                food.getCreatedAt()
        );
    }
}
