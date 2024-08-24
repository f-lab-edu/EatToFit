package com.flab.eattofit.member.application.auth;

import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;

public interface OAuthManager {

    String getAccessToken(String code, OAuthProviderRequest providerRequest);
    OAuthUserResponse getOAuthUserResponse(String token, OAuthProviderRequest provider);
}
