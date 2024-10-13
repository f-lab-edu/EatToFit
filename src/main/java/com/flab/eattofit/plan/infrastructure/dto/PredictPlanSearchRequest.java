package com.flab.eattofit.plan.infrastructure.dto;

import java.math.BigDecimal;
import java.util.List;

public record PredictPlanSearchRequest(
        BigDecimal kcal,
        MemberProfileResponse memberProfile,
        List<String> fitness,
        List<String> sports
) {
}
