package com.flab.eattofit.member.application;

import com.flab.eattofit.member.application.auth.AuthService;
import com.flab.eattofit.member.application.auth.OAuthManager;
import com.flab.eattofit.member.application.auth.dto.LoginRequest;
import com.flab.eattofit.member.domain.auth.RefreshTokenRepository;
import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.domain.member.Member;
import com.flab.eattofit.member.domain.member.MemberRepository;
import com.flab.eattofit.member.domain.member.NicknameGenerator;
import com.flab.eattofit.member.infrastructure.auth.FakeOAuthManager;
import com.flab.eattofit.member.infrastructure.auth.FakeRefreshTokenRepository;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.TokenResponse;
import com.flab.eattofit.member.infrastructure.member.FakeMemberRepository;
import com.flab.eattofit.member.infrastructure.member.FakeNicknameGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.eattofit.member.fixture.OAuthProviderRequestFixture.인증_기관_요청;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private TokenManager tokenManager;
    private RefreshTokenRepository refreshTokenRepository;
    private NicknameGenerator nicknameGenerator;
    private MemberRepository memberRepository;
    private OAuthManager oAuthManager;
    private AuthService authService;

    @BeforeEach
    void init() {
        refreshTokenRepository = new FakeRefreshTokenRepository();
        nicknameGenerator = new FakeNicknameGenerator();
        memberRepository = new FakeMemberRepository();
        oAuthManager = new FakeOAuthManager();
        authService = new AuthService(refreshTokenRepository, nicknameGenerator, memberRepository, oAuthManager, tokenManager);
    }

    @Test
    void 처음_로그인을_진행하면_회원가입_및_토큰을_반환한다() {
        // given
        LoginRequest request = new LoginRequest("kakao", "code");
        OAuthProviderRequest provider = 인증_기관_요청();
        String expectedAccessToken = "access_token";
        String expectedRefreshToken = "refresh_token";
        String nickname = "nickname";
        when(tokenManager.getUserToken(any())).thenReturn(new TokenResponse(expectedAccessToken, expectedRefreshToken));

        // when
        TokenResponse response = authService.login(request, provider);

        // then
        assertSoftly(softly -> {
            softly.assertThat(memberRepository.existsByNickname(nickname)).isTrue();
            softly.assertThat(response.accessToken()).isEqualTo(expectedAccessToken);
            softly.assertThat(response.refreshToken()).isEqualTo(expectedRefreshToken);
        });
    }

    @Test
    void 이메일이_다르고_닉네임이_같은_회원이_로그인하면_닉네임을_변경시킨다() {
        // given
        Member firstMember = Member.of("member1@email.com", "nickname");
        memberRepository.save(firstMember);

        LoginRequest request = new LoginRequest("kakao", "code");
        OAuthProviderRequest provider = 인증_기관_요청();
        String expectedAccessToken = "access_token";
        String expectedRefreshToken = "refresh_token";
        String expectedNickname = nicknameGenerator.generateNickname("nickname");

        when(tokenManager.getUserToken(any())).thenReturn(new TokenResponse(expectedAccessToken, expectedRefreshToken));

        // when
        TokenResponse response = authService.login(request, provider);

        // then
        assertSoftly(softly -> {
            softly.assertThat(memberRepository.existsByNickname(expectedNickname)).isTrue();
            softly.assertThat(response.accessToken()).isEqualTo(expectedAccessToken);
            softly.assertThat(response.refreshToken()).isEqualTo(expectedRefreshToken);
        });
    }
}
