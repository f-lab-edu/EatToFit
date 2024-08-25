package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.application.auth.OAuthConnectionManager;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;

public class FakeOAuthConnectionManager implements OAuthConnectionManager {

    @Override
    public String getOAuthAccessTokenResponse(final String code, final OAuthProviderRequest providerRequest) {
        return "fakeAccessToken";
    }

    @Override
    public String getOAuthUserInfoResponse(final String token, final String userInfoUrl) {
        return "fakeUserInfo";
    }
}
