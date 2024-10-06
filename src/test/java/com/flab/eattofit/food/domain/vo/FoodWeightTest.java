package com.flab.eattofit.food.domain.vo;

import com.flab.eattofit.food.exception.FoodUnitNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FoodWeightTest {

    @Nested
    class 음식_무게_생성 {

        @Test
        void 음식_무게를_단위와_함께_생성한다() {
            // given
            BigDecimal servingSize = BigDecimal.valueOf(123.45);
            String unit = "g";

            // when
            FoodWeight weight = FoodWeight.createWith(servingSize, unit);

            // then
            assertSoftly(softly -> {
                softly.assertThat(weight.getServingSize()).isEqualTo(servingSize);
                softly.assertThat(weight.getUnit()).isEqualTo(FoodUnit.GRAM);
            });
        }

        @Test
        void 없는_무게_단위를_사용하면_예외가_발생한다() {
            // given
            BigDecimal servingSize = BigDecimal.valueOf(123.45);
            String unit = "abc";

            // when & then
            assertThatThrownBy(() -> FoodWeight.createWith(servingSize, unit))
                    .isInstanceOf(FoodUnitNotFoundException.class);
        }
    }
}
