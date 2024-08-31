package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.infrastructure.auth.dto.TokenResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class JsonWebTokenManagerTest {

    @Autowired
    private TokenManager tokenManager;

    @Test
    void 회원의_id로_토큰을_발급한다() {
        // given
        Long id = 1L;

        // when
        TokenResponse response = tokenManager.getUserToken(id);

        // then
        assertThat(response.accessToken()).isNotEmpty();
    }
}
