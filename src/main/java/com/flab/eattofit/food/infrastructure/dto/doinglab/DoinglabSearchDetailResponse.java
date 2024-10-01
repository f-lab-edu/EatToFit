package com.flab.eattofit.food.infrastructure.dto.doinglab;

import java.util.List;

public record DoinglabSearchDetailResponse(
        String name,
        String fullName,
        DoinglabSearchPositionResponse position,
        List<DoinglabSearchCandidateResponse> candidates,
        DoinglabSearchAmountResponse estimatedAmount,
        List<DoinglabSearchIngredientResponse> ingredients
) {
}
