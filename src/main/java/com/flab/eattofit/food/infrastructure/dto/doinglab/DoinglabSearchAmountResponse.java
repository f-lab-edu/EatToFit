package com.flab.eattofit.food.infrastructure.dto.doinglab;

import java.math.BigDecimal;

public record DoinglabSearchAmountResponse(
        BigDecimal estimatedServingSize,
        BigDecimal estimatedServingAmount,
        BigDecimal estimatedVolume,
        BigDecimal estimatedPortion,
        String estimatedPlateType
) {
}
