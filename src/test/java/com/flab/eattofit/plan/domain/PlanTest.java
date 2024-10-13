package com.flab.eattofit.plan.domain;

import com.flab.eattofit.plan.domain.vo.PlanType;
import com.flab.eattofit.plan.exception.PlanTypeNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PlanTest {

    @Nested
    class 플랜_생성 {

        @Test
        void 플랜을_생성한다() {
            // given
            String type = "피트니스";
            Long memberId = 1L;

            // when
            Plan plan = Plan.createWith(type, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(plan.getType()).isEqualTo(PlanType.FITNESS);
                softly.assertThat(plan.getMemberId()).isEqualTo(memberId);
                softly.assertThat(plan.getExercises()).isEmpty();
                softly.assertThat(plan.getIsDone()).isFalse();
            });
        }

        @Test
        void 없는_타입으로_생성하면_예외가_발생한다() {
            // given
            String type = "abc";
            Long memberId = 1L;

            // when & then
            assertThatThrownBy(() -> Plan.createWith(type, memberId))
                    .isInstanceOf(PlanTypeNotFoundException.class);
        }
    }

    @Nested
    class 플랜_운동_추가 {

        @Test
        void 플랜에_피트니스를_추가한다() {
            // given
            String type = "피트니스";
            Long memberId = 1L;
            Plan plan = Plan.createWith(type, memberId);

            String name = "스쿼트";
            int repeat = 10;
            BigDecimal expect = BigDecimal.valueOf(100.5);
            int size = 12;
            BigDecimal weight = BigDecimal.valueOf(50);
            Exercise expectedExercise = Exercise.createFitness(name, repeat, expect, size, weight);

            // when
            plan.addExercise(name, repeat, expect, size, weight, null);

            // then
            assertSoftly(softly -> {
                softly.assertThat(plan.getExercises()).hasSize(1);
                softly.assertThat(plan.getExercises()).contains(expectedExercise);
            });
        }

        @Test
        void 플랜에_스포츠를_추가한다() {
            // given
            String type = "스포츠";
            Long memberId = 1L;
            Plan plan = Plan.createWith(type, memberId);

            String name = "축구";
            BigDecimal expect = BigDecimal.valueOf(100.5);
            int time = 1800;
            Exercise expectedExercise = Exercise.createSports(name, expect, time);

            // when
            plan.addExercise(name, null, expect, null, null, time);

            // then
            assertSoftly(softly -> {
                softly.assertThat(plan.getExercises()).hasSize(1);
                softly.assertThat(plan.getExercises()).contains(expectedExercise);
            });
        }
    }
}
