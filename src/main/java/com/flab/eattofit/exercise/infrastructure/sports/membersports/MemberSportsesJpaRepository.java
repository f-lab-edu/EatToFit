package com.flab.eattofit.exercise.infrastructure.sports.membersports;

import com.flab.eattofit.exercise.domain.fitness.memberfitness.MemberFitnesses;
import com.flab.eattofit.exercise.domain.sports.membersports.MemberSportses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSportsesJpaRepository extends JpaRepository<MemberSportses, Long> {

    MemberSportses save(MemberFitnesses memberFitnesses);
    Optional<MemberSportses> findByMemberId(Long memberId);
}
