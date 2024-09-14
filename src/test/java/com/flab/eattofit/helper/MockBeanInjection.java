package com.flab.eattofit.helper;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.flab.eattofit.member.application.auth.AuthService;
import com.flab.eattofit.member.application.auth.OAuthManager;
import com.flab.eattofit.member.domain.auth.RefreshTokenRepository;
import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.domain.member.MemberRepository;
import com.flab.eattofit.member.domain.member.NicknameGenerator;
import com.flab.eattofit.member.ui.auth.interceptor.MemberLoginValidCheckerInterceptor;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationContext;
import com.flab.eattofit.member.ui.auth.support.MemberAuthenticationExtractor;
import com.flab.eattofit.member.ui.auth.support.OAuthProperties;
import com.flab.eattofit.member.ui.auth.support.resolver.AuthMemberArgumentResolver;
import com.flab.eattofit.member.ui.auth.support.resolver.OAuthArgumentResolver;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@MockBean(JpaMetamodelMappingContext.class)
public class MockBeanInjection {

    // auth
    @MockBean
    protected OAuthArgumentResolver oAuthArgumentResolver;

    @MockBean
    protected AuthMemberArgumentResolver authMemberArgumentResolver;

    @MockBean
    protected MemberLoginValidCheckerInterceptor memberLoginValidCheckerInterceptor;

    @MockBean
    protected MemberAuthenticationContext memberAuthenticationContext;

    @MockBean
    protected MemberAuthenticationExtractor authenticationExtractor;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected OAuthProperties oAuthProperties;

    @MockBean
    protected JsonMapper jsonMapper;

    @MockBean
    protected TokenManager tokenManager;

    @MockBean
    protected OAuthManager oAuthManager;

    @MockBean
    protected NicknameGenerator nicknameGenerator;

    @MockBean
    protected MemberRepository memberRepository;

    @MockBean
    protected RefreshTokenRepository refreshTokenRepository;
}
