package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.TokenManager;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class JsonWebTokenManagerTest {

    @Autowired
    private TokenManager tokenManager;

    @Nested
    class 토큰_발급 {

        @Test
        void 회원의_id로_액세스_토큰을_발급한다() {
            // given
            Long id = 1L;

            // when
            String accessToken = tokenManager.generateAccessToken(id);
            Long extractMemberId = tokenManager.extractMemberId(accessToken);

            // then
            assertSoftly(softly -> {
                softly.assertThat(accessToken).isNotEmpty();
                softly.assertThat(extractMemberId).isEqualTo(id);
            });
        }

        @Test
        void 리프레시_토큰을_발급한다() {
            // given & when
            String refreshToken = tokenManager.generateRefreshToken();

            // then
            assertThat(refreshToken).isNotEmpty();
        }
    }

    @Nested
    class 회원_정보_추출 {

        @Test
        void 회원의_id를_추출한다() {
            // given
            Long id = 1L;
            String accessToken = tokenManager.generateAccessToken(id);

            // when
            Long extractId = tokenManager.extractMemberId(accessToken);

            // then
            assertThat(extractId).isEqualTo(id);
        }
    }
}
