package com.flab.eattofit.member.application.auth;

import com.flab.eattofit.member.application.auth.dto.LoginRequest;
import com.flab.eattofit.member.application.auth.dto.TokenResponse;
import com.flab.eattofit.member.domain.auth.RefreshTokenRepository;
import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.domain.member.NicknameGenerator;
import com.flab.eattofit.member.domain.member.Member;
import com.flab.eattofit.member.domain.member.MemberRepository;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final NicknameGenerator nicknameGenerator;
    private final MemberRepository memberRepository;
    private final OAuthManager oAuthManager;
    private final TokenManager tokenManager;

    public TokenResponse login(final LoginRequest request, final OAuthProviderRequest provider) {
        OAuthUserResponse response = oAuthManager.getOAuthUserResponse(request.code(), provider);
        Member member = memberRepository.findByEmail(response.email())
                .orElseGet(() -> registerOAuthMember(response.email(), response.name()));

        String accessToken = tokenManager.generateAccessToken(member.getId());
        String refreshToken = tokenManager.generateRefreshToken();
        refreshTokenRepository.save(member.getId(), refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }

    private Member registerOAuthMember(final String email, final String name) {
        Member member = Member.of(email, name);
        if (memberRepository.existsByNickname(name)) {
            member.updateNicknameWithGenerator(nicknameGenerator);
        }
        return memberRepository.save(member);
    }
}
