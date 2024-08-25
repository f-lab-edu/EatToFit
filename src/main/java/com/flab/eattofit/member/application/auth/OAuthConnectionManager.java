package com.flab.eattofit.member.application.auth;

import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;

public interface OAuthConnectionManager {

    String getOAuthAccessTokenResponse(String code, OAuthProviderRequest providerRequest);
    String getOAuthUserInfoResponse(String token, String userInfoUrl);
}
