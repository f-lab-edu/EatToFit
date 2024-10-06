package com.flab.eattofit.food.domain.vo;

import com.flab.eattofit.food.exception.FoodUnitNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FoodUnitTest {

    @Nested
    class 음식_무게_단위_조회 {

        @Test
        void 음식_무게_단위를_조회한다() {
            // given
            String value = "g";

            // when
            FoodUnit unit = FoodUnit.findByName(value);

            // then
            assertSoftly(softly -> {
                softly.assertThat(unit).isEqualTo(FoodUnit.GRAM);
                softly.assertThat(unit.getName()).isEqualTo(value);
            });
        }

        @Test
        void 없는_음식_무게_단위를_조회면_예외가_발생한다() {
            // given
            String value = "abc";

            // when & then
            assertThatThrownBy(() -> FoodUnit.findByName(value))
                    .isInstanceOf(FoodUnitNotFoundException.class);
        }
    }
}
