package com.flab.eattofit.member.ui.auth.interceptor;

import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationContext;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class ParseMemberIdFromTokenInterceptor implements HandlerInterceptor {

    private final MemberLoginValidCheckerInterceptor memberLoginValidCheckerInterceptor;
    private final MemberAuthenticationContext memberAuthenticationContext;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (MemberAuthenticationExtractor.extractFromRequest(request).isEmpty()) {
            memberAuthenticationContext.setAnonymous();
            return true;
        }

        return memberLoginValidCheckerInterceptor.preHandle(request, response, handler);
    }
}
