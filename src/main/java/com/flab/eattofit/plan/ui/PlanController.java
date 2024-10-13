package com.flab.eattofit.plan.ui;

import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import com.flab.eattofit.plan.application.PlanService;
import com.flab.eattofit.plan.application.dto.PlanCreateRequest;
import com.flab.eattofit.plan.domain.Plan;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import com.flab.eattofit.plan.ui.dto.PlanCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/plans")
@RestController
public class PlanController {

    private static final String KCAL = "kcal";

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanCreateResponse> createPlan(
            @RequestBody @Valid final PlanCreateRequest request,
            @AuthMember final Long memberId
    ) {
        Plan plan = planService.createPlan(request, memberId);
        return ResponseEntity.created(URI.create("/plans/" + plan.getId()))
                .body(PlanCreateResponse.from(plan));
    }

    @GetMapping("/search")
    public ResponseEntity<PredictPlanSearchResponse> searchPredictPlans(
            final @RequestParam(value = KCAL) BigDecimal kcal,
            @AuthMember final Long memberId
    ) {
        PredictPlanSearchResponse response = planService.planSearch(kcal, memberId);
        return ResponseEntity.ok()
                .body(response);
    }
}
