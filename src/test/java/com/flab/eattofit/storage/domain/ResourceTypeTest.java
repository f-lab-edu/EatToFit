package com.flab.eattofit.storage.domain;

import com.flab.eattofit.storage.exception.exceptions.NotFoundResourceTypeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ResourceTypeTest {

    @Test
    void 스토리지_자원_타입을_조회한다() {
        // given
        String type = "food";
        ResourceType expectedType = ResourceType.FOOD;

        // when
        ResourceType resourceType = ResourceType.findByName(type);

        // then
        assertSoftly(softly -> {
            softly.assertThat(resourceType).isEqualTo(expectedType);
            softly.assertThat(resourceType.getName()).isEqualTo(type);
        });
    }

    @Test
    void 없는_스토리지_자원_타입을_조회하면_예외가_발생한다() {
        // given
        String type = "abc";

        // when & then
        assertThatThrownBy(() -> ResourceType.findByName(type))
                .isInstanceOf(NotFoundResourceTypeException.class);
    }
}
