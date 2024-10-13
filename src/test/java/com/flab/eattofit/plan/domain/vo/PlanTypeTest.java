package com.flab.eattofit.plan.domain.vo;

import com.flab.eattofit.plan.exception.PlanTypeNotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PlanTypeTest {

    @Nested
    class 플랜_타입_조회 {

        @Test
        void 플랜_타입을_조회한다() {
            // given
            String type = "피트니스";

            // when
            PlanType planType = PlanType.findByName(type);

            // then
            assertSoftly(softly -> {
                softly.assertThat(planType).isEqualTo(PlanType.FITNESS);
                softly.assertThat(planType.getValue()).isEqualTo(type);
            });
        }

        @Test
        void 없는_플랜_타입을_조회하면_예외가_발생한다() {
            // given
            String type = "abc";

            // when & then
            assertThatThrownBy(() -> PlanType.findByName(type))
                    .isInstanceOf(PlanTypeNotFoundException.class);
        }
    }
}
