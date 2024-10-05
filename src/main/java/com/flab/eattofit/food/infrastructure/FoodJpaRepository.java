package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodJpaRepository extends JpaRepository<Food, Long> {

    Food save(Food food);
}
