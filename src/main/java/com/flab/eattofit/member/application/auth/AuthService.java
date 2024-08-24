package com.flab.eattofit.member.application.auth;

import com.flab.eattofit.member.application.auth.dto.LoginRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final OAuthManager oAuthManager;

    public String login(final LoginRequest request, final OAuthProviderRequest provider) {
        String accessToken = oAuthManager.getAccessToken(request.code(), provider);
        OAuthUserResponse response = oAuthManager.getOAuthUserResponse(accessToken, provider);
        return response.email();
    }
}
