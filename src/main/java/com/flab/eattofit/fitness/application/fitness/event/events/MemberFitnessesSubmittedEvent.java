package com.flab.eattofit.fitness.application.fitness.event.events;

import java.util.Set;

public record MemberFitnessesSubmittedEvent(
        Set<Long> fitnessIds
) {
}
