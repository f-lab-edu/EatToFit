package com.flab.eattofit.member.domain.auth;

import com.flab.eattofit.member.infrastructure.auth.dto.TokenResponse;

public interface TokenProvider {

    TokenResponse getUserToken(Long id);
}
