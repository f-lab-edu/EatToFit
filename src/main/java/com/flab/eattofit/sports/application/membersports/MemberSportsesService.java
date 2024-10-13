package com.flab.eattofit.sports.application.membersports;

import com.flab.eattofit.sports.application.membersports.dto.MemberSportsesRequest;
import com.flab.eattofit.sports.application.sports.event.events.MemberSportsesSubmittedEvent;
import com.flab.eattofit.sports.domain.membersports.MemberSports;
import com.flab.eattofit.sports.domain.membersports.MemberSportses;
import com.flab.eattofit.sports.domain.membersports.MemberSportsesRepository;
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
