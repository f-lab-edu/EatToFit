package com.flab.eattofit.member.config.auth;

import com.flab.eattofit.global.config.interceptor.PathMatcherInterceptor;
import com.flab.eattofit.member.ui.auth.interceptor.MemberLoginValidCheckerInterceptor;
import com.flab.eattofit.member.ui.auth.interceptor.ParseMemberIdFromTokenInterceptor;
import com.flab.eattofit.member.ui.auth.support.resolver.AuthMemberArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import static com.flab.eattofit.global.config.interceptor.support.HttpMethod.GET;
import static com.flab.eattofit.global.config.interceptor.support.HttpMethod.OPTIONS;
import static com.flab.eattofit.global.config.interceptor.support.HttpMethod.POST;

@RequiredArgsConstructor
@Configuration
public class MemberAuthConfig implements WebMvcConfigurer {

    private final AuthMemberArgumentResolver authMemberArgumentResolver;
    private final ParseMemberIdFromTokenInterceptor parseMemberIdFromTokenInterceptor;
    private final MemberLoginValidCheckerInterceptor memberLoginValidCheckerInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(parseMemberIdFromTokenInterceptor());
        registry.addInterceptor(loginValidCheckerInterceptor());
    }

    private HandlerInterceptor parseMemberIdFromTokenInterceptor() {
        return new PathMatcherInterceptor(parseMemberIdFromTokenInterceptor)
                .excludePathPatterns("/**", OPTIONS);
    }

    private HandlerInterceptor loginValidCheckerInterceptor() {
        return new PathMatcherInterceptor(memberLoginValidCheckerInterceptor)
                .excludePathPatterns("/api/auth/login/**", POST)
                .addPathPatterns("/api/storage/**", GET)
                .addPathPatterns("/api/foods/**", GET);
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authMemberArgumentResolver);
    }
}
