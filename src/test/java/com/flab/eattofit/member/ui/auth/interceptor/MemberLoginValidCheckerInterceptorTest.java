package com.flab.eattofit.member.ui.auth.interceptor;

import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.exception.exceptions.auth.MemberLoginInvalidException;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberLoginValidCheckerInterceptorTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final MemberAuthenticationContext memberAuthenticationContext = mock(MemberAuthenticationContext.class);
    private final TokenManager tokenManager = mock(TokenManager.class);

    @Test
    void 토큰이_없다면_예외가_발생한다() {
        // given
        MemberLoginValidCheckerInterceptor memberLoginValidCheckerInterceptor = new MemberLoginValidCheckerInterceptor(
                memberAuthenticationContext,
                tokenManager
        );
        when(request.getHeader("any")).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> memberLoginValidCheckerInterceptor.preHandle(request, response, new Object()))
                .isInstanceOf(MemberLoginInvalidException.class);
    }
}
