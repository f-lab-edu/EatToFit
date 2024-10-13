package com.flab.eattofit.plan.domain;

import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;

public interface PlanSearchManager {

    PredictPlanSearchResponse searchPlans(PredictPlanSearchRequest request);
}
