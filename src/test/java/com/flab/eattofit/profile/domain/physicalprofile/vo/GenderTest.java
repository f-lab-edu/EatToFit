package com.flab.eattofit.profile.domain.physicalprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.physicalprofile.GenderNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class GenderTest {

    @Nested
    class 성별_조회 {

        @Test
        void 성별_조회() {
            // given
            String name = "남성";

            // when
            Gender gender = Gender.findByName(name);

            // then
            assertSoftly(softly -> {
                softly.assertThat(gender).isEqualTo(Gender.MALE);
                softly.assertThat(gender.getName()).isEqualTo(name);
            });
        }

        @Test
        void 없는_성별을_조회하면_예외가_발생한다() {
            // given
            String name = "abc";

            // when & then
            assertThatThrownBy(() -> Gender.findByName(name))
                    .isInstanceOf(GenderNotFoundException.class);
        }
    }
}
