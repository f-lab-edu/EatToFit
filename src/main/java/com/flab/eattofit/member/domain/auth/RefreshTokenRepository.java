package com.flab.eattofit.member.domain.auth;

public interface RefreshTokenRepository {

    void save(Long id, String token);
}
