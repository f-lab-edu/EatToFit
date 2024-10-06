package com.flab.eattofit.exercise.infrastructure.fitness.memberfitness;

import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnesses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberFitnessesJpaRepository extends JpaRepository<MemberFitnesses, Long> {

    MemberFitnesses save(MemberFitnesses memberFitnesses);
    Optional<MemberFitnesses> findByMemberId(Long memberId);
}
