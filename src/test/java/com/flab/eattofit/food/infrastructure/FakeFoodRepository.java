package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.FoodRepository;

public class FakeFoodRepository implements FoodRepository {

    @Override
    public Food save(final Food food) {
        return null;
    }
}
