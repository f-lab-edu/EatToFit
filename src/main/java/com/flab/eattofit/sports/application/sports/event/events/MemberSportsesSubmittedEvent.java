package com.flab.eattofit.sports.application.sports.event.events;

import java.util.Set;

public record MemberSportsesSubmittedEvent(
        Set<Long> sportsIds
) {
}
