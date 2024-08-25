package com.flab.eattofit.member.fixture;

import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.UserInfoKeyWordRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class OAuthProviderRequestFixture {

    public static OAuthProviderRequest 인증_기관_요청() {
        return new OAuthProviderRequest(
                "clientId",
                "clientSecret",
                "redirectUri",
                "tokenUri",
                "userInfoUri",
                new UserInfoKeyWordRequest("emailKeyWord", "nameKeyWord")
        );
    }
}
