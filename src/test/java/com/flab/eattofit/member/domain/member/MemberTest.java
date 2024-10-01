package com.flab.eattofit.member.domain.member;

import com.flab.eattofit.member.infrastructure.member.FakeNicknameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberTest {

    private final NicknameGenerator nicknameGenerator = new FakeNicknameGenerator();

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

    @Test
    void 회원은_회원가입_시_닉네임을_수정할_수_있다() {
        // given
        String email = "a@email.com";
        String nickname = "hello";
        String updatedName = nicknameGenerator.generateNickname(nickname);
        Member member = Member.of(email, nickname);

        // when
        member.updateNicknameWithGenerator(nicknameGenerator);

        // then
        assertSoftly(softly -> {
            softly.assertThat(member.getNickname()).isNotEqualTo(nickname);
            softly.assertThat(member.getNickname()).isEqualTo(updatedName);
        });
    }
}
