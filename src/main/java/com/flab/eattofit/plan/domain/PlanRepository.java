package com.flab.eattofit.plan.domain;

import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;

import java.math.BigDecimal;

public interface PlanRepository {

    Plan save(Plan plan);
    PredictPlanSearchRequest getPlanSearchRequest(BigDecimal kcal, Long memberId);
}
