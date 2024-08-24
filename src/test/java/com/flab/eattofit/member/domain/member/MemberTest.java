package com.flab.eattofit.member.domain.member;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberTest {

    @Test
    void 회원을_생성한다() {
        // given
        String email = "a@email.com";
        String nickname = "hello";

        // when
        Member member = Member.of(email, nickname);

        // then
        assertSoftly(softly -> {
            softly.assertThat(member.getEmail()).isEqualTo(email);
            softly.assertThat(member.getNickname()).isEqualTo(nickname);
        });
    }
}
