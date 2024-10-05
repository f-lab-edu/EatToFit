package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.GoalNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class GoalTest {

    @Nested
    class 운동_목표_조회 {

        @Test
        void 운동_목표를_조회한다() {
            // given
            String name = "근비대";

            // when
            Goal goal = Goal.findByName(name);

            // then
            assertSoftly(softly -> {
                softly.assertThat(goal).isEqualTo(Goal.INCREASE_MUSCLE);
                softly.assertThat(goal.getName()).isEqualTo(name);
            });
        }

        @Test
        void 없는_운동_목표_조회_시_예외가_발생한다() {
            // given
            String name = "abc";

            // when & then
            assertThatThrownBy(() -> Goal.findByName(name))
                    .isInstanceOf(GoalNotFoundException.class);
        }
    }
}
