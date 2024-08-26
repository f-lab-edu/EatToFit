package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.application.auth.OAuthManager;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;

public class FakeOAuthManager implements OAuthManager {

    @Override
    public OAuthUserResponse getOAuthUserResponse(final String code, final OAuthProviderRequest provider) {
        return new OAuthUserResponse("nickname", "email");
    }
}
