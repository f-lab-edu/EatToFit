package com.flab.eattofit.member.ui.auth.support;

import com.flab.eattofit.member.exception.exceptions.auth.MemberLoginInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberAuthenticationContextTest {

    private MemberAuthenticationContext memberAuthenticationContext;

    @BeforeEach
    void setup() {
        memberAuthenticationContext = new MemberAuthenticationContext();
    }

    @Test
    void member_id를_반환한다() {
        // given
        memberAuthenticationContext.setAuthentication(1L);

        // when
        Long result = memberAuthenticationContext.getPrincipal();

        // then
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void member_id가_없다면_예외를_발생한다() {
        // given
        memberAuthenticationContext.setAuthentication(null);

        // when & then
        assertThatThrownBy(() -> memberAuthenticationContext.getPrincipal())
                .isInstanceOf(MemberLoginInvalidException.class);
    }
}
