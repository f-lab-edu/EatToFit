package com.flab.eattofit.plan.ui;

import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import com.flab.eattofit.plan.application.PlanService;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/api/plans")
@RestController
public class PlanController {

    private static final String KCAL = "kcal";

    private final PlanService planService;

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
