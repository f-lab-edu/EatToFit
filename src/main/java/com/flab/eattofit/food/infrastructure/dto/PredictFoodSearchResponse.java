package com.flab.eattofit.food.infrastructure.dto;

import java.util.List;

public record PredictFoodSearchResponse(
        List<FoodSearchResponse> predictFoods
) {
}
