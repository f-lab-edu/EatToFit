package com.flab.eattofit.fitness.ui.memberfitness;

import com.flab.eattofit.fitness.application.memberfitness.MemberFitnessesService;
import com.flab.eattofit.fitness.application.memberfitness.dto.MemberFitnessesRequest;
import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members/me/fitness")
@RestController
public class MemberFitnessesController {

    private final MemberFitnessesService memberFitnessesService;

    @PostMapping("/prefer-fitness")
    public ResponseEntity<Void> submitMemberFitness(
            @RequestBody @Valid final MemberFitnessesRequest request,
            @AuthMember final Long memberId
    ) {
        memberFitnessesService.submitMemberFitnesses(request, memberId);
        return ResponseEntity.ok()
                .build();
    }
}
