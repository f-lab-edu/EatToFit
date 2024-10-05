package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.FrequencyNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FrequencyTest {

    @Nested
    class 운동_빈도_조회 {

        @Test
        void 운동_빈도를_조회한다() {
            // given
            String name = "주 2회";

            // when
            Frequency frequency = Frequency.findByName(name);

            // then
            assertSoftly(softly -> {
                softly.assertThat(frequency).isEqualTo(Frequency.TWO);
                softly.assertThat(frequency.getName()).isEqualTo(name);
            });
        }

        @Test
        void 없는_운동_빈도_조회_시_예외가_발생한다() {
            // given
            String name = "abc";

            // when & then
            assertThatThrownBy(() -> Frequency.findByName(name))
                    .isInstanceOf(FrequencyNotFoundException.class);
        }
    }
}
