package com.flab.eattofit.member.ui.auth.interceptor;

import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ParseMemberIdFromTokenInterceptorTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final TokenManager tokenManager = mock(TokenManager.class);

    private final MemberAuthenticationContext memberAuthenticationContext = mock(MemberAuthenticationContext.class);
    private MemberLoginValidCheckerInterceptor memberLoginValidCheckerInterceptor;

    @BeforeEach
    void setup() {
        memberLoginValidCheckerInterceptor = new MemberLoginValidCheckerInterceptor(memberAuthenticationContext, tokenManager);
    }

    @Test
    void 요청으로부터_추출한_결과가_비었으면_익명으로_처리된다() throws Exception {
        // given
        ParseMemberIdFromTokenInterceptor parseMemberIdFromTokenInterceptor = new ParseMemberIdFromTokenInterceptor(
                memberLoginValidCheckerInterceptor,
                memberAuthenticationContext
        );
        when(request.getHeader("any")).thenReturn(null);

        // when
        boolean result = parseMemberIdFromTokenInterceptor.preHandle(request, response, new Object());

        // then
        assertThat(result).isTrue();
        verify(memberAuthenticationContext).setAnonymous();
    }
}
