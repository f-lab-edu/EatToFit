package com.flab.eattofit.fitness.application.memberfitness;

import com.flab.eattofit.fitness.application.memberfitness.dto.MemberFitnessesRequest;
import com.flab.eattofit.fitness.application.fitness.event.events.MemberFitnessesSubmittedEvent;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitness;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnessesRepository;
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

    public void submitMemberFitnesses(final MemberFitnessesRequest request, final Long memberId) {
        Set<Long> fitnessIds = Set.copyOf(request.fitnesses());
        MemberFitnesses memberFitnesses = memberFitnessesRepository.findByMemberId(memberId)
                .orElseGet(() -> createDefaultMemberFitnesses(memberId));
        Events.raise(new MemberFitnessesSubmittedEvent(fitnessIds));

        for (Long fitnessId : fitnessIds) {
            memberFitnesses.addFitness(MemberFitness.createWith(fitnessId));
        }
    }

    private MemberFitnesses createDefaultMemberFitnesses(final Long memberId) {
        MemberFitnesses memberFitnesses = MemberFitnesses.createWithMemberId(memberId);
        return memberFitnessesRepository.save(memberFitnesses);
    }
}
