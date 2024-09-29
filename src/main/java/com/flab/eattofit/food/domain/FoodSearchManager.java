package com.flab.eattofit.food.domain;

import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;

public interface FoodSearchManager {

    PredictFoodSearchResponse search(String url);
}
