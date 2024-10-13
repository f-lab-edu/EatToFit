package com.flab.eattofit.plan.application;

import com.flab.eattofit.plan.domain.PlanRepository;
import com.flab.eattofit.plan.domain.PlanSearchManager;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Transactional
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanSearchManager planSearchManager;

    @Transactional(readOnly = true)
    public PredictPlanSearchResponse planSearch(final BigDecimal kcal, final Long memberId) {
        PredictPlanSearchRequest planSearchRequest = planRepository.getPlanSearchRequest(kcal, memberId);
        return planSearchManager.searchPlans(planSearchRequest);
    }
}
