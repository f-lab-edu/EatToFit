package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.ExperienceNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ExperienceTest {

    @Nested
    class 운동_경력_조회 {

        @Test
        void 운동_경력을_조회한다() {
            // given
            String name = "3개월 미만";

            // when
            Experience experience = Experience.findByName(name);

            // then
            assertSoftly(softly -> {
                softly.assertThat(experience).isEqualTo(Experience.UNDER_THREE_MONTH);
                softly.assertThat(experience.getName()).isEqualTo(name);
            });
        }

        @Test
        void 없는_운동_경력_조회_시_예외가_발생한다() {
            // given
            String name = "abc";

            // when & then
            assertThatThrownBy(() -> Experience.findByName(name))
                    .isInstanceOf(ExperienceNotFoundException.class);
        }
    }
}
