package com.flab.eattofit.profile.fixture.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Experience;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Frequency;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Goal;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Level;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ExerciseProfileFixture {

    public static ExerciseProfile 운동_정보_프로필(
            String experience,
            String frequency,
            String goal,
            String level,
            long memberId
    ) {
        return ExerciseProfile.builder()
                .experience(Experience.findByName(experience))
                .frequency(Frequency.findByName(frequency))
                .goal(Goal.findByName(goal))
                .level(Level.findByName(level))
                .memberId(memberId)
                .build();
    }
}
