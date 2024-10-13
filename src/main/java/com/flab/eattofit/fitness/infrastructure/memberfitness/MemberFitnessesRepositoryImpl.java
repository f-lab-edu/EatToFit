package com.flab.eattofit.fitness.infrastructure.memberfitness;

import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnessesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberFitnessesRepositoryImpl implements MemberFitnessesRepository {

    private final MemberFitnessesJpaRepository memberFitnessesJpaRepository;

    @Override
    public MemberFitnesses save(final MemberFitnesses memberFitnesses) {
        return memberFitnessesJpaRepository.save(memberFitnesses);
    }

    @Override
    public Optional<MemberFitnesses> findByMemberId(final Long memberId) {
        return memberFitnessesJpaRepository.findByMemberId(memberId);
    }
}
