package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.TokenProvider;
import com.flab.eattofit.member.exception.exceptions.auth.JwtExpiredException;
import com.flab.eattofit.member.exception.exceptions.auth.JwtFormatInvalidException;
import com.flab.eattofit.member.exception.exceptions.auth.JwtSignVerifyException;
import com.flab.eattofit.member.exception.exceptions.auth.NotSupportedTokenException;
import com.flab.eattofit.member.infrastructure.auth.dto.TokenResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JsonWebTokenProvider implements TokenProvider {

    private static final String ID = "id";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration-period}")
    private int accessTokenExpirationPeriod;

    @Value("${jwt.refresh-token-expiration-period}")
    private int refreshTokenExpirationPeriod;

    @Override
    public TokenResponse getUserToken(final Long id) {
        String accessToken = generateAccessToken(id);
        String refreshToken = generateRefreshToken(id);

        return new TokenResponse(accessToken, refreshToken);
    }

    private String generateAccessToken(final Long id) {
        return Jwts.builder()
                .subject(ACCESS_TOKEN)
                .claim(ID, id)
                .issuedAt(createIssuedAt())
                .signWith(generateJwtSignKey())
                .expiration(createAccessTokenExpiredAt(accessTokenExpirationPeriod))
                .compact();
    }

    private Date createIssuedAt() {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private SecretKey generateJwtSignKey() {
        String encoded = encodeJwtSecretKey();
        byte[] secretBytes = Decoders.BASE64.decode(encoded);

        return Keys.hmacShaKeyFor(secretBytes);
    }

    private String encodeJwtSecretKey() {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Encoders.BASE64.encode(secretBytes);
    }

    private Date createAccessTokenExpiredAt(final int period) {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.plusHours(period)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private String generateRefreshToken(final Long id) {
        return Jwts.builder()
                .subject(REFRESH_TOKEN)
                .issuedAt(createIssuedAt())
                .signWith(generateJwtSignKey())
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
                    .verifyWith(generateJwtSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get(ID);
            return Long.valueOf(id);
        } catch (SecurityException exception) {
            throw new JwtSignVerifyException();
        } catch (MalformedJwtException exception) {
            throw new JwtFormatInvalidException();
        } catch (UnsupportedJwtException exception) {
            throw new NotSupportedTokenException();
        } catch (ExpiredJwtException exception) {
            throw new JwtExpiredException();
        }
    }
}
