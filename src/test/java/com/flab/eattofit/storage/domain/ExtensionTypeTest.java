package com.flab.eattofit.storage.domain;

import com.flab.eattofit.storage.exception.exceptions.NotFoundExtensionTypeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ExtensionTypeTest {

    @Test
    void 확장자_타입을_조회한다() {
        // given
        String type = "jpg";
        ExtensionType expectedType = ExtensionType.JPG;

        // when
        ExtensionType extension = ExtensionType.findByName(type);

        // then
        assertSoftly(softly -> {
            softly.assertThat(extension).isEqualTo(expectedType);
            softly.assertThat(extension.getName()).isEqualTo(type);
        });
    }

    @Test
    void 없는_확장자_조회_시_예외가_발생한다() {
        // given
        String type = "abc";

        // when & then
        assertThatThrownBy(() -> ExtensionType.findByName(type))
                .isInstanceOf(NotFoundExtensionTypeException.class);
    }
}
