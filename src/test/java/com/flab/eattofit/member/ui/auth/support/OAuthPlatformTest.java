package com.flab.eattofit.member.ui.auth.support;

import com.flab.eattofit.member.exception.exceptions.auth.OAuthPlatformNotFoundException;
import com.flab.eattofit.member.ui.auth.support.OAuthPlatform;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class OAuthPlatformTest {

    @Test
    void 등록된_인증_기관을_이름으로_찾는다() {
        // given
        String name = "kakao";

        // when
        OAuthPlatform platform = OAuthPlatform.findPlatform(name);

        // then
        assertThat(platform.getName()).isEqualTo(name);
    }

    @Test
    void 존재하지_않는_인증_기관을_찾는_경우_예외가_발생한다() {
        // given
        String name = "abc";

        // when & then
        assertThatThrownBy(() -> OAuthPlatform.findPlatform(name))
                .isInstanceOf(OAuthPlatformNotFoundException.class);
    }
}
