package com.flab.eattofit.food.application;

import com.flab.eattofit.food.application.dto.FoodCreateRequest;
import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.FoodRepository;
import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodService {

    private static final String FOOD_SEARCH_MANAGER = "doinglabFoodSearchManager";

    private final FoodRepository foodRepository;
    private final FoodSearchManager foodSearchManager;

    public FoodService(
            final FoodRepository foodRepository,
            @Qualifier(value = FOOD_SEARCH_MANAGER) final FoodSearchManager foodSearchManager
    ) {
        this.foodRepository = foodRepository;
        this.foodSearchManager = foodSearchManager;
    }

    public Food createFood(final FoodCreateRequest request, final Long memberId) {
        FoodWeight weight = FoodWeight.createWith(request.servingSize(), request.unit());
        FoodNutrient nutrient = FoodNutrient.builder()
                .kcal(request.kcal())
                .carbohydrate(request.carbohydrate())
                .protein(request.protein())
                .fat(request.fat())
                .sodium(request.sodium())
                .build();
        Food food = Food.createWith(request.name(), weight, nutrient, request.url(), memberId);

        return foodRepository.save(food);
    }

    public PredictFoodSearchResponse foodSearch(final Long memberId, final String url) {
        log.info("{} 회원이 {}로 음식 검색 요청", memberId, url);
        return foodSearchManager.search(url);
    }
}
