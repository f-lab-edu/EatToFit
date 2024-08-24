package com.flab.eattofit.member.application.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "OAuth 소셜 기관이 필요합니다.")
        String provider,

        @NotBlank(message = "OAuth 소셜 기관의 코드가 필요합니다.")
        String code
) {
}
