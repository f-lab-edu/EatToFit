package com.flab.eattofit.exercise.infrastructure.sports.membersports;

import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportses;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportsesRepository;
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
