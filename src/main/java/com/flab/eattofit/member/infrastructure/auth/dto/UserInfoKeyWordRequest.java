package com.flab.eattofit.member.infrastructure.auth.dto;

public record UserInfoKeyWordRequest(
        String emailKeyWord,
        String nameKeyWord
) {
}
