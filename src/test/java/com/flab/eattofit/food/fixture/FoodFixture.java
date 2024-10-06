package com.flab.eattofit.food.fixture;

import com.flab.eattofit.food.application.dto.FoodCreateRequest;
import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.time.LocalDateTime;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FoodFixture {

    public static Food 음식_생성_응답_id있음(final FoodCreateRequest request, final Long memberId) {
        FoodWeight weight = FoodWeight.createWith(request.servingSize(), request.unit());
        FoodNutrient nutrient = FoodNutrient.createWith(
                request.kcal(),
                request.kcal(),
                request.protein(),
                request.fat(),
                request.sodium()
        );

        return Food.builder()
                .id(1L)
                .name(request.name())
                .weight(weight)
                .nutrient(nutrient)
                .url(request.url())
                .memberId(memberId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
