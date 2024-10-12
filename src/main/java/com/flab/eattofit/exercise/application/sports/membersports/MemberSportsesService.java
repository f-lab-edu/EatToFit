package com.flab.eattofit.exercise.application.sports.membersports;

import com.flab.eattofit.exercise.application.sports.membersports.dto.MemberSportsesRequest;
import com.flab.eattofit.exercise.application.sports.sports.event.events.MemberSportsesSubmittedEvent;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSports;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportses;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportsesRepository;
import com.flab.eattofit.global.config.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberSportsesService {

    private final MemberSportsesRepository memberSportsesRepository;

    public void submitMemberSportes(final MemberSportsesRequest request, final Long memberId) {
        Set<Long> sportsIds = Set.copyOf(request.sportses());
        MemberSportses memberSportses = memberSportsesRepository.findByMemberId(memberId)
                .orElseGet(() -> createDefaultMemberSportes(memberId));
        Events.raise(new MemberSportsesSubmittedEvent(sportsIds));

        for (Long sportsId : sportsIds) {
            memberSportses.addSports(MemberSports.createWith(sportsId));
        }
    }

    private MemberSportses createDefaultMemberSportes(final Long memberId) {
        MemberSportses memberSportses = MemberSportses.createWithMemberId(memberId);
        return memberSportsesRepository.save(memberSportses);
    }
}
