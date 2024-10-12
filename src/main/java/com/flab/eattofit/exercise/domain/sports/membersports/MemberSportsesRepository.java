package com.flab.eattofit.exercise.domain.sports.membersports;

import java.util.Optional;

public interface MemberSportsesRepository {

    MemberSportses save(MemberSportses memberSportses);
    Optional<MemberSportses> findByMemberId(Long memberId);
}
