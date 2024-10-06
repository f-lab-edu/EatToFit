package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.FoodRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeFoodRepository implements FoodRepository {

    private final Map<Long, Food> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public Food save(final Food food) {
        Food savedFood = Food.builder()
                .id(id)
                .name(food.getName())
                .weight(food.getWeight())
                .nutrient(food.getNutrient())
                .memberId(food.getMemberId())
                .createdAt(food.getCreatedAt())
                .build();

        map.put(id++, savedFood);
        return savedFood;
    }
}
