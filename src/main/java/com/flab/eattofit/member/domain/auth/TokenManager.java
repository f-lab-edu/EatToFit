package com.flab.eattofit.member.domain.auth;

public interface TokenManager {

    String generateAccessToken(Long id);
    String generateRefreshToken();
    Long extractMemberId(String token);
}
