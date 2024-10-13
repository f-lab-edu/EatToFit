package com.flab.eattofit.sports.domain.sports;

import com.flab.eattofit.sports.domain.sports.Sports;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class SportsTest {

    @Test
    void 스포츠를_생성한다() {
        // given
        String name = "축구";

        // when
        Sports sports = Sports.from(name);

        // then
        assertThat(sports.getName()).isEqualTo(name);
    }
}
