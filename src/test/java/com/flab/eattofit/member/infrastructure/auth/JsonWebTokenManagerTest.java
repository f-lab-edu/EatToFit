package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.exception.exceptions.auth.JwtInvalidException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        /*
        @Test
        void 만료된_JWT를_해석하면_예외가_발생한다() {
            // given
            String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3NfdG9rZW4iLCJpZCI6MSwiaWF0IjoxNzI1Mjc4NjQwLCJleHAiOjE3MjUyNzg2NDF9.7ksKcGKJ06tLQcdlc_7CI5ypeOthBwDTOvcAAFdI8eza7XNIvBgKz9Qt7GLvEU5r_Uj18F5gSClIAuSjuspqTA";

            // when & then
            assertThatThrownBy(() -> tokenManager.extractMemberId(token))
                    .isInstanceOf(JwtExpiredException.class);
        }
        */
        
        @Test
        void 변조된_JWT를_해석하면_예외가_발생한다() {
            // given
            String token = "abcd";

            // when & then
            assertThatThrownBy(() -> tokenManager.extractMemberId(token))
                    .isInstanceOf(JwtInvalidException.class);
        }
    }
}
