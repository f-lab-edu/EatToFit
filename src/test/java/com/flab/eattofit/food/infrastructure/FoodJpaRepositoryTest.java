package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class FoodJpaRepositoryTest {

    @Autowired
    private FoodJpaRepository foodJpaRepository;

    @Test
    void 음식을_생성한다() {
        // given
        FoodWeight weight = FoodWeight.createWith(BigDecimal.valueOf(150.0), "g");
        FoodNutrient nutrient = FoodNutrient.createWith(
                BigDecimal.valueOf(430.0),
                BigDecimal.valueOf(36.0),
                BigDecimal.valueOf(25.0),
                BigDecimal.valueOf(21.0),
                BigDecimal.valueOf(636.0)
        );
        Long memberId = 1L;
        String url = "burger.jpg";

        Food food = Food.createWith("햄버거", weight, nutrient, url, memberId);

        // when
        Food savedFood = foodJpaRepository.save(food);

        // then
        assertThat(savedFood).usingRecursiveComparison()
                .ignoringFields("id", "createdAt")
                .isEqualTo(food);
    }
}
