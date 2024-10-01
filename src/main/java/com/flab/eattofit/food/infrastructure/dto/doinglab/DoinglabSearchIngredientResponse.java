package com.flab.eattofit.food.infrastructure.dto.doinglab;

import java.math.BigDecimal;

public record DoinglabSearchIngredientResponse(
        String name,
        BigDecimal gram
) {
}
