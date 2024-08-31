package com.flab.eattofit.member.domain.auth;

import com.flab.eattofit.member.infrastructure.auth.dto.TokenResponse;

public interface TokenManager {

    TokenResponse getUserToken(Long id);
    Long extractMemberId(String token);
}
