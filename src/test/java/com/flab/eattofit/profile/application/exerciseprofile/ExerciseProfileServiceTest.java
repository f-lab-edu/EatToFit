package com.flab.eattofit.profile.application.exerciseprofile;

import com.flab.eattofit.profile.application.exerciseprofile.dto.ExerciseProfileCreateRequest;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfileRepository;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Experience;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Frequency;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Goal;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Level;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.AlreadyCreatedExerciseProfileException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.ExperienceNotFoundException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.FrequencyNotFoundException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.GoalNotFoundException;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.LevelNotFoundException;
import com.flab.eattofit.profile.infrastructure.exerciseprofile.FakeExerciseProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ExerciseProfileServiceTest {

    private ExerciseProfileService exerciseProfileService;
    private ExerciseProfileRepository exerciseProfileRepository;

    @BeforeEach
    void init() {
        exerciseProfileRepository = new FakeExerciseProfileRepository();
        exerciseProfileService = new ExerciseProfileService(exerciseProfileRepository);
    }

    @Nested
    class 운동_프로필_생성 {

        @Test
        void 운동_프로필을_생성한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "초보자";
            Long memberId = 1L;

            ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);

            // when
            ExerciseProfile exerciseProfile = exerciseProfileService.createExerciseProfile(request, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(exerciseProfile.getId()).isEqualTo(1L);
                softly.assertThat(exerciseProfile.getExperience()).isEqualTo(Experience.OVER_ONE_YEAR);
                softly.assertThat(exerciseProfile.getFrequency()).isEqualTo(Frequency.FOUR);
                softly.assertThat(exerciseProfile.getGoal()).isEqualTo(Goal.INCREASE_MUSCLE);
                softly.assertThat(exerciseProfile.getLevel()).isEqualTo(Level.NEWBIE);
                softly.assertThat(exerciseProfile.getMemberId()).isEqualTo(memberId);
            });
        }

        @Test
        void 이미_운동_프로필을_생성했으면_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "초보자";
            Long memberId = 1L;

            ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);
            exerciseProfileService.createExerciseProfile(request, memberId);

            // when & then
            assertThatThrownBy(() -> exerciseProfileService.createExerciseProfile(request, memberId))
                    .isInstanceOf(AlreadyCreatedExerciseProfileException.class);
        }

        @Test
        void 없는_운동_경력을_선택하면_예외가_발생한다() {
            // given
            String experience = "abc";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "초보자";
            Long memberId = 1L;

            ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);

            // when & then
            assertThatThrownBy(() -> exerciseProfileService.createExerciseProfile(request, memberId))
                    .isInstanceOf(ExperienceNotFoundException.class);
        }

        @Test
        void 없는_운동_빈도를_선택하면_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "abc";
            String goal = "근비대";
            String level = "초보자";
            Long memberId = 1L;

            ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);

            // when & then
            assertThatThrownBy(() -> exerciseProfileService.createExerciseProfile(request, memberId))
                    .isInstanceOf(FrequencyNotFoundException.class);
        }

        @Test
        void 없는_운동_목표를_선택하면_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "abc";
            String level = "초보자";
            Long memberId = 1L;

            ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);

            // when & then
            assertThatThrownBy(() -> exerciseProfileService.createExerciseProfile(request, memberId))
                    .isInstanceOf(GoalNotFoundException.class);
        }

        @Test
        void 없는_운동_레벨을_선택하면_예외가_발생한다() {
            // given
            String experience = "1년 이상";
            String frequency = "주 4회";
            String goal = "근비대";
            String level = "abc";
            Long memberId = 1L;

            ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);

            // when & then
            assertThatThrownBy(() -> exerciseProfileService.createExerciseProfile(request, memberId))
                    .isInstanceOf(LevelNotFoundException.class);
        }
    }
}
