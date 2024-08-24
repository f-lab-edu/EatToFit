package com.flab.eattofit.member.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberTest {

    @Test
    void 회원을_생성한다() {
        // given
        String nickname = "hello";

        // when
        Member member = Member.from(nickname);

        // then
        assertThat(member.getNickname()).isEqualTo(nickname);
    }
}
