package com.flab.eattofit.profile.infrastructure.exerciseprofile;

import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseProfileJpaRepository extends JpaRepository<ExerciseProfile, Long> {

    ExerciseProfile save(ExerciseProfile exerciseProfile);
    boolean existsByMemberId(Long memberId);
}
