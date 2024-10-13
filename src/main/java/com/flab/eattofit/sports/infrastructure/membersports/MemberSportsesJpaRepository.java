package com.flab.eattofit.sports.infrastructure.membersports;

import com.flab.eattofit.fitness.domain.memberfitness.MemberFitnesses;
import com.flab.eattofit.sports.domain.membersports.MemberSportses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSportsesJpaRepository extends JpaRepository<MemberSportses, Long> {

    MemberSportses save(MemberFitnesses memberFitnesses);
    Optional<MemberSportses> findByMemberId(Long memberId);
}
