package com.flab.eattofit.food.application;

import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodSearchManager foodSearchManager;

    public PredictFoodSearchResponse foodSearch(String url) {
        return foodSearchManager.search(url);
    }
}
