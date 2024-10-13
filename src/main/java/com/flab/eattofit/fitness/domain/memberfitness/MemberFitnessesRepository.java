package com.flab.eattofit.fitness.domain.memberfitness;

import java.util.Optional;

public interface MemberFitnessesRepository {

    MemberFitnesses save(MemberFitnesses memberFitnesses);
    Optional<MemberFitnesses> findByMemberId(Long memberId);
}
