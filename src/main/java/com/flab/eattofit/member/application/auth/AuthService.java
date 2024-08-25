package com.flab.eattofit.member.application.auth;

import com.flab.eattofit.member.application.auth.dto.LoginRequest;
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

    private final NicknameGenerator nicknameGenerator;
    private final MemberRepository memberRepository;
    private final OAuthManager oAuthManager;

    public String login(final LoginRequest request, final OAuthProviderRequest provider) {
        OAuthUserResponse response = extractOAuthUser(request, provider);
        Member member = memberRepository.findByEmail(response.email())
                .orElseGet(() -> createMember(response.email(), response.name()));

        return member.getEmail();
    }

    private OAuthUserResponse extractOAuthUser(final LoginRequest request, final OAuthProviderRequest provider) {
        String accessToken = oAuthManager.getAccessToken(request.code(), provider);
        return oAuthManager.getOAuthUserResponse(accessToken, provider);
    }

    private Member createMember(final String email, final String name) {
        if (memberRepository.existsByNickname(name)) {
            String convertedName = nicknameGenerator.generateNickname(name);
            return memberRepository.save(Member.of(email, convertedName));
        }
        Member member = Member.of(email, name);
        return memberRepository.save(member);
    }
}
