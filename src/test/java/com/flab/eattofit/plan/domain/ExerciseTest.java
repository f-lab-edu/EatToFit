package com.flab.eattofit.plan.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ExerciseTest {

    @Nested
    class 운동_생성 {

        @Test
        void 피트니스를_생성한다() {
            // given
            String name = "스쿼트";
            int repeat = 10;
            BigDecimal expect = BigDecimal.valueOf(100.5);
            int size = 12;
            BigDecimal weight = BigDecimal.valueOf(50);

            // when
            Exercise fitness = Exercise.createFitness(name, repeat, expect, size, weight);

            // then
            assertSoftly(softly -> {
                softly.assertThat(fitness.getName()).isEqualTo(name);
                softly.assertThat(fitness.getRepeat()).isEqualTo(repeat);
                softly.assertThat(fitness.getSize()).isEqualTo(size);
                softly.assertThat(fitness.getWeight()).isEqualTo(weight);
                softly.assertThat(fitness.getTime()).isNull();
                softly.assertThat(fitness.getIsDone()).isFalse();
            });
        }

        @Test
        void 스포츠를_생성한다() {
            // given
            String name = "축구";
            BigDecimal expect = BigDecimal.valueOf(100.5);
            int time = 1800;

            // when
            Exercise sports = Exercise.createSports(name, expect, time);

            // then
            assertSoftly(softly -> {
                softly.assertThat(sports.getName()).isEqualTo(name);
                softly.assertThat(sports.getRepeat()).isNull();
                softly.assertThat(sports.getSize()).isNull();
                softly.assertThat(sports.getWeight()).isNull();
                softly.assertThat(sports.getTime()).isEqualTo(time);
                softly.assertThat(sports.getIsDone()).isFalse();
            });
        }
    }
}
