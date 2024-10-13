package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.plan.infrastructure.dto.MemberProfileResponse;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import static com.flab.eattofit.exercise.domain.fitness.fitness.QFitness.fitness;
import static com.flab.eattofit.exercise.domain.fitness.memberfitness.QMemberFitness.memberFitness;
import static com.flab.eattofit.exercise.domain.fitness.memberfitness.QMemberFitnesses.memberFitnesses;
import static com.flab.eattofit.exercise.domain.sports.membersports.QMemberSports.memberSports;
import static com.flab.eattofit.exercise.domain.sports.membersports.QMemberSportses.memberSportses;
import static com.flab.eattofit.exercise.domain.sports.sports.QSports.sports;
import static com.flab.eattofit.member.domain.member.QMember.member;
import static com.flab.eattofit.profile.domain.exerciseprofile.QExerciseProfile.exerciseProfile;
import static com.flab.eattofit.profile.domain.physicalprofile.QPhysicalProfile.physicalProfile;
import static com.querydsl.core.types.Projections.constructor;

@RequiredArgsConstructor
@Repository
public class PlanQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PredictPlanSearchRequest getPredictPlanSearchRequest(final BigDecimal kcal, final Long memberId) {
        MemberProfileResponse memberProfileResponse = getMemberProfileResponse(memberId);
        List<String> preferFitnessNames = getMemberPreferFitnessNames(memberId);
        List<String> preferSportsNames = getMemberPreferSportsNames(memberId);

        return new PredictPlanSearchRequest(kcal, memberProfileResponse, preferFitnessNames, preferSportsNames);
    }

    private MemberProfileResponse getMemberProfileResponse(final Long memberId) {
        return jpaQueryFactory.select(constructor(MemberProfileResponse.class,
                        exerciseProfile.experience.stringValue(),
                        exerciseProfile.frequency.stringValue(),
                        exerciseProfile.goal.stringValue(),
                        exerciseProfile.level.stringValue(),
                        physicalProfile.physical.birthYear.value,
                        physicalProfile.physical.gender.stringValue(),
                        physicalProfile.physical.weight.value,
                        physicalProfile.physical.height.value))
                .from(member)
                .innerJoin(exerciseProfile).on(member.id.eq(exerciseProfile.memberId))
                .innerJoin(physicalProfile).on(member.id.eq(physicalProfile.memberId))
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    private List<String> getMemberPreferFitnessNames(final Long memberId) {
        return jpaQueryFactory.select(fitness.name)
                .from(fitness)
                .innerJoin(memberFitness).on(memberFitness.fitnessId.eq(fitness.id))
                .innerJoin(memberFitnesses).on(memberFitnesses.memberId.eq(memberId))
                .fetchJoin()
                .where(memberFitnesses.memberId.eq(memberId))
                .fetch();
    }

    private List<String> getMemberPreferSportsNames(final Long memberId) {
        return jpaQueryFactory.select(sports.name)
                .from(sports)
                .innerJoin(memberSports).on(memberSports.sportsId.eq(sports.id))
                .innerJoin(memberSportses).on(memberSportses.memberId.eq(memberId))
                .fetchJoin()
                .where(memberSportses.memberId.eq(memberId))
                .fetch();
    }
}
