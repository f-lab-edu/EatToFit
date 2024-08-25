package com.flab.eattofit.member.infrastructure.auth.dto;

public record OAuthUserResponse(
        String name,
        String email
) {
}
