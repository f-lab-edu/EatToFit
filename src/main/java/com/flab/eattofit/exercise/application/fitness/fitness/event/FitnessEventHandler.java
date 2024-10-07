package com.flab.eattofit.exercise.application.fitness.fitness.event;

import com.flab.eattofit.exercise.application.fitness.fitness.FitnessService;
import com.flab.eattofit.exercise.application.fitness.fitness.event.events.MemberFitnessesSubmittedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FitnessEventHandler {

    private final FitnessService fitnessService;

    @EventListener
    public void validateMemberFitnessIds(final MemberFitnessesSubmittedEvent event) {
        fitnessService.validateFitnessIds(event.fitnessIds());
    }
}
