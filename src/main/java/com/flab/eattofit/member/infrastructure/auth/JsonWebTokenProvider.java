package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.TokenProvider;
import com.flab.eattofit.member.infrastructure.auth.dto.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
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
    private static final String TOKEN_TYPE = "token_type";
    private static final String ACCESS_TOKEN = "access_token";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration-period}")
    private int accessTokenExpirationPeriod;

    @Override
    public TokenResponse getUserToken(final Long id) {
        String accessToken = Jwts.builder()
                .claim(ID, id)
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .issuedAt(createIssuedAt())
                .signWith(generateJwtSignKey())
                .expiration(createExpiredAt(accessTokenExpirationPeriod))
                .compact();

        return new TokenResponse(accessToken);
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

    private Date createIssuedAt() {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private Date createExpiredAt(final int period) {
        LocalDateTime now = LocalDateTime.now();

        return Date.from(now.plusHours(period)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
