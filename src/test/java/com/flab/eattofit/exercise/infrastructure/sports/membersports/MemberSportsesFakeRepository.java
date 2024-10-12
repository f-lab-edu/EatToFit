package com.flab.eattofit.exercise.infrastructure.sports.membersports;

import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportses;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportsesRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberSportsesFakeRepository implements MemberSportsesRepository {

    private final Map<Long, MemberSportses> map = new HashMap<>();
    private Long id = 1L;

    @Override
    public MemberSportses save(final MemberSportses memberSportses) {
        MemberSportses savedMemberSportses = MemberSportses.builder()
                .id(id)
                .memberId(memberSportses.getMemberId())
                .sports(memberSportses.getSports())
                .build();

        map.put(id++, savedMemberSportses);
        return savedMemberSportses;
    }

    @Override
    public Optional<MemberSportses> findByMemberId(final Long memberId) {
        return map.values().stream()
                .filter(memberSportses -> memberId.equals(memberSportses.getMemberId()))
                .findAny();
    }
}
