package com.flab.eattofit.plan.application;

import com.flab.eattofit.plan.application.dto.ExerciseCreateRequest;
import com.flab.eattofit.plan.application.dto.PlanCreateRequest;
import com.flab.eattofit.plan.domain.Plan;
import com.flab.eattofit.plan.domain.PlanRepository;
import com.flab.eattofit.plan.domain.PlanSearchManager;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanSearchManager planSearchManager;

    public Plan createPlan(final PlanCreateRequest request, final Long memberId) {
        Plan plan = Plan.createWith(request.type(), memberId);
        List<ExerciseCreateRequest> exercises = request.exercises();
        for (ExerciseCreateRequest exercise : exercises) {
            plan.addExercise(exercise.name(), exercise.repeat(), exercise.expect(), exercise.size(), exercise.weight(), exercise.time());
        }

        return planRepository.save(plan);
    }

    @Transactional(readOnly = true)
    public PredictPlanSearchResponse planSearch(final BigDecimal kcal, final Long memberId) {
        log.info("{} 회원이 {} kcal 플랜 검색 요청", memberId, kcal.doubleValue());
        PredictPlanSearchRequest planSearchRequest = planRepository.getPlanSearchRequest(kcal, memberId);
        return planSearchManager.searchPlans(planSearchRequest);
    }
}
