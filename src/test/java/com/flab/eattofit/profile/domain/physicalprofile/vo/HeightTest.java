package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.physicalprofile.BadMemberHeightException;
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
class HeightTest {

    @Nested
    class 신장_생성 {

        @Test
        void 신장을_생성한다() {
            // given
            BigDecimal value = BigDecimal.valueOf(172.3);

            // when
            Height height = Height.createWith(value);

            // then
            assertThat(height.getValue()).isEqualTo(value);
        }

        @ParameterizedTest(name = "신장이 {0}cm일 경우")
        @ValueSource(doubles = {-10.5, 10.2, 300})
        void 최소_신장보다_작거나_최대_신장보다_크면_예외가_발생한다(final Double value) {
            // given
            BigDecimal height = BigDecimal.valueOf(value);

            // when & then
            assertThatThrownBy(() -> Height.createWith(height))
                    .isInstanceOf(BadMemberHeightException.class);
        }
    }
}
