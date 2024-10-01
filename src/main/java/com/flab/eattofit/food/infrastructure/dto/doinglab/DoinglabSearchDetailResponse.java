package com.flab.eattofit.food.infrastructure.dto.doinglab;

import java.util.List;

public record DoinglabSearchDetailResponse(
        String fullName,
        List<DoinglabSearchCandidateResponse> candidates
) {
}
