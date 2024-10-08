package com.flab.eattofit.exercise.application.sports.sports.event;

import com.flab.eattofit.exercise.application.sports.sports.SportsService;
import com.flab.eattofit.exercise.application.sports.sports.event.events.MemberSportsesSubmittedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SportsEventHandler {

    private final SportsService sportsService;

    @EventListener
    public void validateMemberSportsIds(final MemberSportsesSubmittedEvent event) {
        sportsService.validateSportsIds(event.sportsIds());
    }
}
