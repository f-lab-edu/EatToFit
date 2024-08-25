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
        OAuthUserResponse response = oAuthManager.getOAuthUserResponse(request.code(), provider);
        Member member = memberRepository.findByEmail(response.email())
                .orElseGet(() -> createMember(response.email(), response.name()));

        return member.getEmail();
    }

    private Member createMember(final String email, final String name) {
        Member member = Member.of(email, name);
        if (memberRepository.existsByNickname(name)) {
            member.updateNicknameWithGenerator(nicknameGenerator);
        }
        return memberRepository.save(member);
    }
}
