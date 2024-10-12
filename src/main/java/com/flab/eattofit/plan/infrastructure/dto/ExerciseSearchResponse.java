package com.flab.eattofit.plan.infrastructure.dto;

import java.math.BigDecimal;

public sealed interface ExerciseSearchResponse permits FitnessSearchResponse, SportsSearchResponse {
    String name();
    BigDecimal expect();
}
