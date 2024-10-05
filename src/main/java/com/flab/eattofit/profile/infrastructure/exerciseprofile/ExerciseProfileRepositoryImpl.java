package com.flab.eattofit.profile.infrastructure.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ExerciseProfileRepositoryImpl implements ExerciseProfileRepository {

    private final ExerciseProfileJpaRepository exerciseProfileJpaRepository;

    @Override
    public ExerciseProfile save(final ExerciseProfile exerciseProfile) {
        return exerciseProfileJpaRepository.save(exerciseProfile);
    }

    @Override
    public boolean isExistedByMemberId(final Long memberId) {
        return exerciseProfileJpaRepository.existsByMemberId(memberId);
    }
}
