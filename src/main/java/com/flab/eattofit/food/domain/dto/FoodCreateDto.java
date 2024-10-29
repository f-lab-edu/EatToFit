package com.flab.eattofit.food.domain.dto;

import com.flab.eattofit.food.application.dto.FoodCreateRequest;

import java.math.BigDecimal;

public record FoodCreateDto(
        Long memberId,
        String name,
        BigDecimal servingSize,
        String unit,
        BigDecimal kcal,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        BigDecimal sodium,
        String url
) {

    public static FoodCreateDto createWith(final FoodCreateRequest request, final Long memberId) {
        return new FoodCreateDto(
                memberId,
                request.name(),
                request.servingSize(),
                request.unit(),
                request.kcal(),
                request.carbohydrate(),
                request.protein(),
                request.fat(),
                request.sodium(),
                request.url()
        );
    }
}
