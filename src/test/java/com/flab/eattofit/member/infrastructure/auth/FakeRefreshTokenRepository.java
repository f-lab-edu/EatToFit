package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.RefreshTokenRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeRefreshTokenRepository implements RefreshTokenRepository {

    private final Map<Long, String> map = new HashMap<>();

    @Override
    public void save(final Long id, final String token) {
        map.put(id, token);
    }
}
