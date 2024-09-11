package com.flab.eattofit.member.ui.auth.support;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberAuthenticationExtractorTest {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private MemberAuthenticationExtractor memberAuthenticationExtractor;

    @BeforeEach
    void setup() {
        memberAuthenticationExtractor = new MemberAuthenticationExtractor();
    }

    @Nested
    class 요청에서_토큰_추출 {

        @Test
        void 토큰이_정상적으로_조회된다() {
            // given
            String expectedResponseToken = BEARER + " token";
            when(request.getHeader(AUTHORIZATION_HEADER)).thenReturn(expectedResponseToken);

            // when
            Optional<String> result = memberAuthenticationExtractor.extractFromRequest(request);

            // then
            assertSoftly(softly -> {
                softly.assertThat(result).isPresent();
                softly.assertThat(result).isEqualTo(Optional.of("token"));
            });
        }

        @Test
        void 토큰_헤더가_없다면_빈_값이_반환된다() {
            // given
            when(request.getHeader(AUTHORIZATION_HEADER)).thenReturn("InvalidType token");

            // when
            Optional<String> result = memberAuthenticationExtractor.extractFromRequest(request);

            // then
            assertThat(result).isEmpty();
        }
    }
}
