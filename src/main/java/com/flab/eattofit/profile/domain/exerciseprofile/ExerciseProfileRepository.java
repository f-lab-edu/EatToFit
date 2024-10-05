package com.flab.eattofit.profile.domain.exerciseprofile;

public interface ExerciseProfileRepository {

    ExerciseProfile save(ExerciseProfile exerciseProfile);
    boolean isExistedByMemberId(Long memberId);
}
