package com.flab.eattofit.member.application.auth.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
