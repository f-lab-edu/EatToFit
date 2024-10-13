package com.flab.eattofit.plan.infrastructure.dto;

import java.math.BigDecimal;

public record MemberProfileResponse(
        String experience,
        String frequency,
        String goal,
        String level,
        Integer birthYear,
        String gender,
        BigDecimal weight,
        BigDecimal height
) {
}
