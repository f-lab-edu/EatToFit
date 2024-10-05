package com.flab.eattofit.profile.domain.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.vo.Experience;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Frequency;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Goal;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Level;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.ExperienceNotFoundException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.FrequencyNotFoundException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.GoalNotFoundException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.LevelNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ExerciseProfileTest {

    @Nested
    class 운동_프로필_생성 {

        @Test
        void 운동_프로필을_생성한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "전문가";
            Long memberId = 1L;

            // when
            ExerciseProfile exerciseProfile = ExerciseProfile.createWith(experience, frequency, goal, level, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(exerciseProfile.getExperience()).isEqualTo(Experience.OVER_ONE_YEAR);
                softly.assertThat(exerciseProfile.getFrequency()).isEqualTo(Frequency.FOUR);
                softly.assertThat(exerciseProfile.getGoal()).isEqualTo(Goal.INCREASE_MUSCLE);
                softly.assertThat(exerciseProfile.getLevel()).isEqualTo(Level.EXPERT);
                softly.assertThat(exerciseProfile.getMemberId()).isEqualTo(memberId);
            });
        }

        @Test
        void 없는_운동_경력으로_생성_시_예외가_발생한다() {
            // given
            String experience = "abc";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "전문가";
            Long memberId = 1L;

            // when & then
            assertThatThrownBy(() -> ExerciseProfile.createWith(experience, frequency, goal, level, memberId))
                    .isInstanceOf(ExperienceNotFoundException.class);
        }

        @Test
        void 없는_운동_빈도로_생성_시_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "abc";
            String goal = "근비대";
            String level = "전문가";
            Long memberId = 1L;

            // when & then
            assertThatThrownBy(() -> ExerciseProfile.createWith(experience, frequency, goal, level, memberId))
                    .isInstanceOf(FrequencyNotFoundException.class);
        }

        @Test
        void 없는_운동_목표로_생성_시_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "abc";
            String level = "전문가";
            Long memberId = 1L;

            // when & then
            assertThatThrownBy(() -> ExerciseProfile.createWith(experience, frequency, goal, level, memberId))
                    .isInstanceOf(GoalNotFoundException.class);
        }

        @Test
        void 없는_운동_레벨로_생성_시_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "abc";
            Long memberId = 1L;

            // when & then
            assertThatThrownBy(() -> ExerciseProfile.createWith(experience, frequency, goal, level, memberId))
                    .isInstanceOf(LevelNotFoundException.class);
        }
    }
}
