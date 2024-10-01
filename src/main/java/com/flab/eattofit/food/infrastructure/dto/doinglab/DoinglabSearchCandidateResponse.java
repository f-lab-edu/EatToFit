package com.flab.eattofit.food.infrastructure.dto.doinglab;

import java.math.BigDecimal;

public record DoinglabSearchCandidateResponse(
        Long id,
        String manufacturer,
        String foodName,
        String fullFoodName,
        String foodType,
        BigDecimal servingSize,
        BigDecimal totalServingSize,
        String unit,
        String counts,
        BigDecimal energy,
        BigDecimal carbohydrate,
        BigDecimal protein,
        BigDecimal fat,
        BigDecimal totalSugars,
        BigDecimal totalDietaryFiber,
        BigDecimal calcium,
        BigDecimal iron,
        BigDecimal magnesium,
        BigDecimal phosphorus,
        BigDecimal potassium,
        BigDecimal sodium,
        BigDecimal zinc,
        BigDecimal selenium,
        BigDecimal retinol,
        BigDecimal betaCarotene,
        BigDecimal vitaminA,
        BigDecimal thiamin,
        BigDecimal riboflavin,
        BigDecimal niacin,
        BigDecimal vitaminB6,
        BigDecimal biotin,
        BigDecimal totalFolate,
        BigDecimal vitaminC,
        BigDecimal vitaminD,
        BigDecimal vitaminE,
        BigDecimal vitaminK,
        BigDecimal isoleucine,
        BigDecimal leucine,
        BigDecimal valine,
        BigDecimal bcaa,
        BigDecimal cholesterol,
        BigDecimal saturatedFattyAcid,
        BigDecimal epa,
        BigDecimal dha,
        BigDecimal omega3FattyAcid,
        BigDecimal omega6FattyAcid,
        BigDecimal transFattyAcid,
        BigDecimal grainGroup,
        BigDecimal proteinGroup,
        BigDecimal vegetableGroup,
        BigDecimal fruitGroup,
        BigDecimal oilGroup,
        BigDecimal sugarGroup,
        BigDecimal dairyGroup,
        BigDecimal nutsGroup,
        BigDecimal unhealthyOilGroup
) {
}
