package com.flab.eattofit.member.ui.auth.interceptor;

import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.exception.exceptions.auth.MemberLoginInvalidException;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationContext;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class MemberLoginValidCheckerInterceptor implements HandlerInterceptor {

    private final MemberAuthenticationContext memberAuthenticationContext;
    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        String token = MemberAuthenticationExtractor.extractFromRequest(request)
                .orElseThrow(MemberLoginInvalidException::new);
        Long extractedId = tokenManager.extractMemberId(token);
        memberAuthenticationContext.setAuthentication(extractedId);

        return true;
    }
}
