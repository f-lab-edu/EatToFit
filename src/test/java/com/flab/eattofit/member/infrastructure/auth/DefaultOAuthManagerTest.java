package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.application.auth.OAuthJsonMapper;
import com.flab.eattofit.member.application.auth.OAuthManager;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;
import com.flab.eattofit.member.infrastructure.auth.dto.UserInfoKeyWordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.eattofit.member.fixture.OAuthProviderRequestFixture.인증_기관_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class DefaultOAuthManagerTest {

    @Mock
    private OAuthJsonMapper jsonMapper;
    private OAuthManager oAuthManager;

    @BeforeEach
    void init() {
        oAuthManager = new DefaultOAuthManager(new FakeOAuthConnectionManager(), jsonMapper);
    }

    @Test
    void 인증기관에_회원_정보를_요청한다() {
        // given
        String code = "code";
        OAuthProviderRequest request = 인증_기관_요청();
        OAuthUserResponse response = new OAuthUserResponse("name", "email");
        when(jsonMapper.extractUserInfo(anyString(), any(UserInfoKeyWordRequest.class))).thenReturn(response);

        // when
        OAuthUserResponse userInfo = oAuthManager.getOAuthUserResponse(code, request);

        // then
        assertThat(userInfo).isEqualTo(response);
    }
}
