package com.flab.eattofit.fitness.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FitnessTest {

    @Test
    void 피트니스를_생성한다() {
        // given
        String name = "덤벨프레스";

        // when
        Fitness fitness = Fitness.from(name);

        // then
        assertThat(fitness.getName()).isEqualTo(name);
    }
}
