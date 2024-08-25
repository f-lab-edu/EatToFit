package com.flab.eattofit.member.infrastructure.member;

import com.flab.eattofit.member.domain.member.NicknameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RandomNicknameGeneratorTest {

    private final NicknameGenerator nicknameGenerator = new RandomNicknameGenerator();

    @Test
    void 닉네임을_새롭게_생성할_수_있다() {
        // given
        String nickname = "nickname";

        // when
        String newNickname = nicknameGenerator.generateNickname(nickname);

        // then
        assertSoftly(softly -> {
            softly.assertThat(newNickname).isNotBlank();
            softly.assertThat(newNickname).isNotEqualTo(nickname);
        });
    }
}
