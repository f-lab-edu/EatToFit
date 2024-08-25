package com.flab.eattofit.member.infrastructure.member;

import com.flab.eattofit.member.domain.member.NicknameGenerator;

public class FakeNicknameGenerator implements NicknameGenerator {

    @Override
    public String generateNickname(final String originName) {
        return originName + " changed";
    }
}
