package com.flab.eattofit.exercise.domain.fitness.memberfitness;

import java.util.Optional;

public interface MemberFitnessesRepository {

    MemberFitnesses save(MemberFitnesses memberFitnesses);
    Optional<MemberFitnesses> findByMemberId(Long memberId);
}
