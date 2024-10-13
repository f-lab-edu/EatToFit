package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.plan.domain.PlanRepository;
import com.flab.eattofit.plan.infrastructure.dto.MemberProfileResponse;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Experience;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Frequency;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Goal;
import com.flab.eattofit.profile.domain.exerciseprofile.vo.Level;
import com.flab.eattofit.profile.domain.physicalprofile.vo.Gender;

import java.math.BigDecimal;
import java.util.List;

public class PlanFakeRepository implements PlanRepository {

    @Override
    public PredictPlanSearchRequest getPlanSearchRequest(final BigDecimal kcal, final Long memberId) {
        return new PredictPlanSearchRequest(
                kcal,
                new MemberProfileResponse(
                        Experience.UNDER_THREE_MONTH.getName(),
                        Frequency.THREE.getName(),
                        Goal.INCREASE_MUSCLE.getName(),
                        Level.INTERMEDIATE.getName(),
                        2000,
                        Gender.MALE.getName(),
                        BigDecimal.valueOf(67),
                        BigDecimal.valueOf(172)
                ),
                List.of("프론트 레이즈", "사이드 레터럴 레이즈", "레그 프레스"),
                List.of("축구")
        );
    }
}
