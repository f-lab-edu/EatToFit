package com.flab.eattofit.exercise.application.sports.sports.event.events;

import java.util.Set;

public record MemberSportsesSubmittedEvent(
        Set<Long> sportsIds
) {
}
