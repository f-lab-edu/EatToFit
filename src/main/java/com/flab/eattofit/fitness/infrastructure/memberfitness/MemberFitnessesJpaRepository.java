package com.flab.eattofit.fitness.infrastructure.memberfitness;

import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberFitnessesJpaRepository extends JpaRepository<MemberFitnesses, Long> {

    MemberFitnesses save(MemberFitnesses memberFitnesses);
    Optional<MemberFitnesses> findByMemberId(Long memberId);
}
