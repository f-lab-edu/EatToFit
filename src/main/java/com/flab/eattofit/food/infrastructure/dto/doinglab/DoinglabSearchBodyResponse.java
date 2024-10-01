package com.flab.eattofit.food.infrastructure.dto.doinglab;

import java.util.List;

public record DoinglabSearchBodyResponse(
        List<DoinglabSearchDetailResponse> body,
        Boolean success,
        String code
) {
}
