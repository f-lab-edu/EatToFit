package com.flab.eattofit.plan.ui.dto;

import com.flab.eattofit.plan.domain.Plan;

import java.time.LocalDateTime;

public record PlanCreateResponse(
        Long id,
        String type,
        Boolean isDone,
        LocalDateTime createdAt
) {

    public static PlanCreateResponse from(final Plan plan) {
        return new PlanCreateResponse(
                plan.getId(),
                plan.getType().getValue(),
                plan.getIsDone(),
                plan.getCreatedAt()
        );
    }
}
