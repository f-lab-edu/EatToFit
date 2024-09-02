package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisRefreshTokenRepository implements RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(final Long id, final String token) {
        redisTemplate.opsForValue().set(String.valueOf(id), token);
    }
}
