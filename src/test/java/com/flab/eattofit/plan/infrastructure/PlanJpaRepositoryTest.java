package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.plan.domain.Plan;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class PlanJpaRepositoryTest {

    @Autowired
    private PlanJpaRepository planJpaRepository;

    @Nested
    class 플랜_생성 {

        @Test
        void 플랜을_생성한다() {
            // given
            String type = "피트니스";
            Long memberId = 1L;
            Plan plan = Plan.createWith(type, memberId);

            // when
            Plan savedPlan = planJpaRepository.save(plan);

            // then
            assertThat(savedPlan).usingRecursiveComparison()
                    .ignoringFields("id", "createdAt")
                    .isEqualTo(plan);
        }

        @Test
        void 플랜을_운동과_함께_생성한다() {
            // given
            String type = "피트니스";
            Long memberId = 1L;
            Plan plan = Plan.createWith(type, memberId);
            plan.addExercise("스쿼트", 10, BigDecimal.valueOf(100.5), 12, BigDecimal.valueOf(50), null);

            // when
            Plan savedPlan = planJpaRepository.save(plan);

            // then
            assertThat(savedPlan.getExercises()).hasSize(1);
        }
    }

}
