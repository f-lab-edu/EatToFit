package com.flab.eattofit.food.domain.vo;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FoodNutrientTest {

    @Nested
    class 음식_영양성분_생성 {

        @Test
        void 음식_영양성분을_생성한다() {
            // given
            BigDecimal kcal = BigDecimal.valueOf(635.31);
            BigDecimal carbohydrate = BigDecimal.valueOf(97.13);
            BigDecimal protein = BigDecimal.valueOf(24.21);
            BigDecimal fat = BigDecimal.valueOf(16.23);
            BigDecimal sodium = BigDecimal.valueOf(1248.24);

            // when
            FoodNutrient nutrient = FoodNutrient.createWith(kcal, carbohydrate, protein, fat, sodium);

            // then
            assertSoftly(softly -> {
               softly.assertThat(nutrient.getKcal()).isEqualTo(kcal);
               softly.assertThat(nutrient.getCarbohydrate()).isEqualTo(carbohydrate);
               softly.assertThat(nutrient.getProtein()).isEqualTo(protein);
               softly.assertThat(nutrient.getFat()).isEqualTo(fat);
               softly.assertThat(nutrient.getSodium()).isEqualTo(sodium);
            });
        }
    }
}
