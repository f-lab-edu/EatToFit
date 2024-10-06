package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FoodRepositoryImpl implements FoodRepository {

    private final FoodJpaRepository foodJpaRepository;

    @Override
    public Food save(final Food food) {
        return foodJpaRepository.save(food);
    }
}
