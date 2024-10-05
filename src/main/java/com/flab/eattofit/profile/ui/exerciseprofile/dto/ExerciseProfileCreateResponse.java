package com.flab.eattofit.profile.ui.exerciseprofile.dto;

import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;

public record ExerciseProfileCreateResponse(
        String experience,
        String frequency,
        String goal,
        String level,
        Long memberId
) {

    public static ExerciseProfileCreateResponse createWith(final ExerciseProfile exerciseProfile) {
        return new ExerciseProfileCreateResponse(
                exerciseProfile.getExperience().getName(),
                exerciseProfile.getFrequency().getName(),
                exerciseProfile.getGoal().getName(),
                exerciseProfile.getLevel().getName(),
                exerciseProfile.getMemberId()
        );
    }
}
