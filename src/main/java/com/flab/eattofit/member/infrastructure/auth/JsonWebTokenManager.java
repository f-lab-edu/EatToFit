package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.exception.exceptions.auth.JwtExpiredException;
import com.flab.eattofit.member.exception.exceptions.auth.JwtInvalidException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JsonWebTokenManager implements TokenManager {

    private static final String ID = "id";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";

    @Value("${jwt.access-secret}")
    private String accessSecret;

    @Value("${jwt.refresh-secret}")
    private String refreshSecret;

    @Value("${jwt.access-token-expiration-period}")
    private int accessTokenExpirationPeriod;

    @Value("${jwt.refresh-token-expiration-period}")
    private int refreshTokenExpirationPeriod;

    @Override
    public String generateAccessToken(final Long id) {
        return Jwts.builder()
                .subject(ACCESS_TOKEN)
                .claim(ID, id)
                .issuedAt(createIssuedAt())
                .signWith(generateSignKey(accessSecret))
                .expiration(createAccessTokenExpiredAt(accessTokenExpirationPeriod))
                .compact();
    }

    private Date createIssuedAt() {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private SecretKey generateSignKey(final String secret) {
        String encoded = encodeJwtSecretKey(secret);
        byte[] secretBytes = Decoders.BASE64.decode(encoded);

        return Keys.hmacShaKeyFor(secretBytes);
    }

    private String encodeJwtSecretKey(final String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Encoders.BASE64.encode(secretBytes);
    }

    private Date createAccessTokenExpiredAt(final int period) {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.plusHours(period)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    @Override
    public String generateRefreshToken() {
        return Jwts.builder()
                .subject(REFRESH_TOKEN)
                .issuedAt(createIssuedAt())
                .signWith(generateSignKey(refreshSecret))
                .expiration(createRefreshTokenExpiredAt(refreshTokenExpirationPeriod))
                .compact();
    }

    private Date createRefreshTokenExpiredAt(final int period) {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.plusDays(period)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    @Override
    public Long extractMemberId(final String token) {
        try {
            Integer id = (Integer) Jwts.parser()
                    .verifyWith(generateSignKey(accessSecret))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get(ID);
            return Long.valueOf(id);
        } catch (ExpiredJwtException exception) {
            throw new JwtExpiredException();
        } catch (JwtException exception) {
            log.error(exception.getMessage());
            throw new JwtInvalidException();
        }
    }
}
