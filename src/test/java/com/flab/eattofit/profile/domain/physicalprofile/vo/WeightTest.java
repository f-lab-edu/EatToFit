package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberWeightException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class WeightTest {

    @Nested
    class 몸무게_생성 {

        @Test
        void 몸무게를_생성한다() {
            // given
            BigDecimal value = BigDecimal.valueOf(60);

            // when
            Weight weight = Weight.createWith(value);

            // then
            assertThat(weight.getValue()).isEqualTo(value);
        }

        @ParameterizedTest(name = "몸무게가 {0}kg일 경우")
        @ValueSource(doubles = {-10.5, 10.2, 300})
        void 최소_몸무게보다_작거나_최대_몸무게보다_크면_예외가_발생한다(final Double value) {
            // given
            BigDecimal weight = BigDecimal.valueOf(value);

            // when & then
            assertThatThrownBy(() -> Weight.createWith(weight))
                    .isInstanceOf(BadMemberWeightException.class);
        }
    }
}
