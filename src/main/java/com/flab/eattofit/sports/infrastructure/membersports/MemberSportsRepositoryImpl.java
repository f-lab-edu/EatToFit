package com.flab.eattofit.sports.infrastructure.membersports;

import com.flab.eattofit.sports.domain.membersports.MemberSportses;
import com.flab.eattofit.sports.domain.membersports.MemberSportsesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberSportsRepositoryImpl implements MemberSportsesRepository {

    private final MemberSportsesJpaRepository memberSportsesJpaRepository;

    @Override
    public MemberSportses save(MemberSportses memberSportses) {
        return memberSportsesJpaRepository.save(memberSportses);
    }

    @Override
    public Optional<MemberSportses> findByMemberId(Long memberId) {
        return memberSportsesJpaRepository.findByMemberId(memberId);
    }
}
