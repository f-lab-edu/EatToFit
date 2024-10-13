package com.flab.eattofit.sports.domain.membersports;

import java.util.Optional;

public interface MemberSportsesRepository {

    MemberSportses save(MemberSportses memberSportses);
    Optional<MemberSportses> findByMemberId(Long memberId);
}
