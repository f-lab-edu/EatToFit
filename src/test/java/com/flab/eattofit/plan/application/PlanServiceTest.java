package com.flab.eattofit.plan.application;

import com.flab.eattofit.global.exception.exceptions.AiResponseParseException;
import com.flab.eattofit.plan.application.dto.PlanCreateRequest;
import com.flab.eattofit.plan.domain.Plan;
import com.flab.eattofit.plan.domain.PlanRepository;
import com.flab.eattofit.plan.domain.PlanSearchManager;
import com.flab.eattofit.plan.domain.vo.PlanType;
import com.flab.eattofit.plan.exception.PlanTypeNotFoundException;
import com.flab.eattofit.plan.infrastructure.PlanFakeRepository;
import com.flab.eattofit.plan.infrastructure.dto.PlanSearchResponse;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import static com.flab.eattofit.plan.fixture.PlanCreateRequestFixture.플랜_생성_요청_없는_타입;
import static com.flab.eattofit.plan.fixture.PlanCreateRequestFixture.플랜_생성_요청_피트니스_세개;
import static com.flab.eattofit.plan.fixture.PlanSearchResponseFixture.플랜_응답_270;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class PlanServiceTest {

    @Mock
    private PlanSearchManager planSearchManager;

    private PlanRepository planRepository;

    private PlanService planService;

    @BeforeEach
    void init() {
        planRepository = new PlanFakeRepository();
        planService = new PlanService(planRepository, planSearchManager);
    }

    @Nested
    class 플랜_생성 {

        @Test
        void 플랜을_생성한다() {
            // given
            PlanCreateRequest request = 플랜_생성_요청_피트니스_세개();
            Long memberId = 1L;

            // when
            Plan plan = planService.createPlan(request, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(plan.getType()).isEqualTo(PlanType.FITNESS);
                softly.assertThat(plan.getMemberId()).isEqualTo(memberId);
                softly.assertThat(plan.getExercises()).hasSize(3);
                softly.assertThat(plan.getIsDone()).isFalse();
            });
        }

        @Test
        void 없는_타입으로_플랜을_생성하면_예외가_발생한다() {
            // given
            PlanCreateRequest request = 플랜_생성_요청_없는_타입();
            Long memberId = 1L;

            // when & then
            assertThatThrownBy(() -> planService.createPlan(request, memberId))
                    .isInstanceOf(PlanTypeNotFoundException.class);
        }
    }

    @Nested
    class 플랜_검색 {

        @Test
        void 플랜을_검색한다() {
            // given
            BigDecimal kcal = BigDecimal.valueOf(270);
            Long memberId = 1L;
            PredictPlanSearchRequest request = planRepository.getPlanSearchRequest(kcal, memberId);
            when(planSearchManager.searchPlans(request)).thenReturn(new PredictPlanSearchResponse(플랜_응답_270()));

            // when
            PredictPlanSearchResponse response = planService.planSearch(kcal, memberId);

            // then
            assertSoftly(softly -> {
                softly.assertThat(response.plans()).isNotEmpty();
                softly.assertThat(response.plans()).extracting(PlanSearchResponse::type)
                        .containsExactly(
                                "피트니스",
                                "스포츠"
                        );
            });
        }

        @Test
        void 반환_형태가_다르면_예외가_발생한다() {
            // given
            BigDecimal kcal = BigDecimal.valueOf(270);
            Long memberId = 1L;
            PredictPlanSearchRequest request = planRepository.getPlanSearchRequest(kcal, memberId);
            when(planSearchManager.searchPlans(request)).thenThrow(AiResponseParseException.class);

            // when & then
            assertThatThrownBy(() -> planService.planSearch(kcal, memberId))
                    .isInstanceOf(AiResponseParseException.class);
        }
    }
}
