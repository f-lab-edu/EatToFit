package com.flab.eattofit.profile.application.physicalprofile.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PhysicalProfileCreateRequest(
        @NotNull(message = "출생 년도가 필요합니다.")
        Integer birthYear,

        @NotEmpty(message = "성별이 필요합니다.")
        String gender,

        @NotNull(message = "몸무게가 필요합니다.")
        BigDecimal weight,

        @NotNull(message = "신장이 필요합니다.")
        BigDecimal height
) {
}
