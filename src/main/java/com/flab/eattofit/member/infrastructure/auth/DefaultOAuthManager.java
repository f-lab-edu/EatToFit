package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.application.auth.OAuthManager;
import com.flab.eattofit.member.application.auth.OAuthConnectionManager;
import com.flab.eattofit.member.application.auth.OAuthJsonMapper;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultOAuthManager implements OAuthManager {

    private static final String KEY = "access_token";

    private final OAuthConnectionManager oAuthConnectionManager;
    private final OAuthJsonMapper jsonMapper;

    @Override
    public OAuthUserResponse getOAuthUserResponse(final String code, final OAuthProviderRequest provider) {
        String accessToken = getAccessToken(code, provider);
        String response = oAuthConnectionManager.getOAuthUserInfoResponse(accessToken, provider.userInfoUri());

        return jsonMapper.extractUserInfo(response, provider.userInfoKeyWordRequest());
    }

    private String getAccessToken(final String code, final OAuthProviderRequest providerRequest) {
        String oAuthAccessTokenResponse = oAuthConnectionManager.getOAuthAccessTokenResponse(code, providerRequest);
        return jsonMapper.extractValueByKey(oAuthAccessTokenResponse, KEY);
    }
}
