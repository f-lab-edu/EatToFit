package com.flab.eattofit.food.application;

import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodService {

    private static final String FOOD_SEARCH_MANAGER = "doinglabFoodSearchManager";

    private final FoodSearchManager foodSearchManager;

    public FoodService(@Qualifier(value = FOOD_SEARCH_MANAGER) final FoodSearchManager foodSearchManager) {
        this.foodSearchManager = foodSearchManager;
    }

    public PredictFoodSearchResponse foodSearch(final Long memberId, final String url) {
        log.info("{} 회원이 {}로 음식 검색 요청", memberId, url);
        return foodSearchManager.search(url);
    }
}
