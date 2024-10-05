package com.flab.eattofit.profile.infrastructure.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfileRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeExerciseProfileRepository implements ExerciseProfileRepository {

    private final Map<Long, ExerciseProfile> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public ExerciseProfile save(final ExerciseProfile exerciseProfile) {
        ExerciseProfile savedExerciseProfile = ExerciseProfile.builder()
                .id(id)
                .experience(exerciseProfile.getExperience())
                .frequency(exerciseProfile.getFrequency())
                .goal(exerciseProfile.getGoal())
                .level(exerciseProfile.getLevel())
                .memberId(exerciseProfile.getMemberId())
                .build();

        map.put(id++, savedExerciseProfile);
        return savedExerciseProfile;
    }

    @Override
    public boolean isExistedByMemberId(final Long memberId) {
        return map.values().stream()
                .anyMatch(profile -> memberId.equals(profile.getMemberId()));
    }
}
