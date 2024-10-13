package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.plan.domain.PlanRepository;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class PlanRepositoryImpl implements PlanRepository {

    private final PlanQueryRepository planQueryRepository;

    @Override
    public PredictPlanSearchRequest getPlanSearchRequest(final BigDecimal kcal, final Long memberId) {
        return planQueryRepository.getPredictPlanSearchRequest(kcal, memberId);
    }
}
