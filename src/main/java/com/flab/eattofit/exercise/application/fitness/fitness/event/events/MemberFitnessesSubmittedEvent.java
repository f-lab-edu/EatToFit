package com.flab.eattofit.exercise.application.fitness.fitness.event.events;

import java.util.Set;

public record MemberFitnessesSubmittedEvent(
        Set<Long> fitnessIds
) {
}
