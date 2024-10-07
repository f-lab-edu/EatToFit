package com.flab.eattofit.exercise.infrastructure.fitness.memberfitness;

import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnesses;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnessesRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberFitnessesFakeRepository implements MemberFitnessesRepository {

    private final Map<Long, MemberFitnesses> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public MemberFitnesses save(final MemberFitnesses memberFitnesses) {
        MemberFitnesses savedMemberFitnesses = MemberFitnesses.builder()
                .id(id)
                .memberId(memberFitnesses.getMemberId())
                .fitnesses(memberFitnesses.getFitnesses())
                .build();

        map.put(id++, savedMemberFitnesses);
        return savedMemberFitnesses;
    }

    @Override
    public Optional<MemberFitnesses> findByMemberId(final Long memberId) {
        return map.values().stream()
                .filter(memberFitnesses -> memberId.equals(memberFitnesses.getMemberId()))
                .findAny();
    }
}
