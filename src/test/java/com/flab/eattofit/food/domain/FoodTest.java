package com.flab.eattofit.food.domain;

import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FoodTest {

    @Nested
    class 음식_생성 {

        @Test
        void 음식을_생성한다() {
            // given
            String name = "햄버거";
            BigDecimal servingSize = BigDecimal.valueOf(123.45);
            String unit = "g";
            BigDecimal kcal = BigDecimal.valueOf(230.4);
            BigDecimal carbohydrate = BigDecimal.valueOf(250);
            BigDecimal protein = BigDecimal.valueOf(120);
            BigDecimal fat = BigDecimal.valueOf(10);
            BigDecimal sodium = BigDecimal.valueOf(20);
            Long memberId = 1L;
            String url = "burger.jpg";

            FoodWeight weight = FoodWeight.createWith(servingSize, unit);
            FoodNutrient nutrient = FoodNutrient.createWith(kcal, carbohydrate, protein, fat, sodium);

            // when
            Food food = Food.createWith(name, weight, nutrient, url, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(food.getName()).isEqualTo(name);
                softly.assertThat(food.getWeight()).usingRecursiveComparison()
                                .isEqualTo(weight);
                softly.assertThat(food.getNutrient()).usingRecursiveComparison()
                                .isEqualTo(nutrient);
                softly.assertThat(food.getMemberId()).isEqualTo(memberId);
            });
        }
    }
}
