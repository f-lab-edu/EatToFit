package com.flab.eattofit.exercise.application.fitness.memberfitness;

import com.flab.eattofit.exercise.application.fitness.memberfitness.dto.MemberFitnessesRequest;
import com.flab.eattofit.exercise.application.fitness.fitness.event.events.MemberFitnessesSubmittedEvent;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitness;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnesses;
import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnessesRepository;
import com.flab.eattofit.global.config.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberFitnessesService {

    private final MemberFitnessesRepository memberFitnessesRepository;

    public MemberFitnesses submitMemberFitnesses(final MemberFitnessesRequest request, final Long memberId) {
        Set<Long> fitnessIds = Set.copyOf(request.fitnesses());
        MemberFitnesses memberFitnesses = memberFitnessesRepository.findByMemberId(memberId)
                .orElseGet(() -> createDefaultMemberFitnesses(memberId));
        Events.raise(new MemberFitnessesSubmittedEvent(fitnessIds));

        for (Long fitnessId : fitnessIds) {
            memberFitnesses.addFitness(MemberFitness.createWith(fitnessId));
        }

        return memberFitnesses;
    }

    private MemberFitnesses createDefaultMemberFitnesses(final Long memberId) {
        MemberFitnesses memberFitnesses = MemberFitnesses.createWithMemberId(memberId);
        return memberFitnessesRepository.save(memberFitnesses);
    }
}
