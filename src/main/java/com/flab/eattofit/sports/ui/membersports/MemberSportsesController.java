package com.flab.eattofit.sports.ui.membersports;

import com.flab.eattofit.sports.application.membersports.MemberSportsesService;
import com.flab.eattofit.sports.application.membersports.dto.MemberSportsesRequest;
import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members/me/sports")
@RestController
public class MemberSportsesController {

    private final MemberSportsesService memberSportsesService;

    @PostMapping("/prefer-sports")
    public ResponseEntity<Void> submitMemberSports(
            @RequestBody @Valid final MemberSportsesRequest request,
            @AuthMember final Long memberId
    ) {
        memberSportsesService.submitMemberSportes(request, memberId);
        return ResponseEntity.ok()
                .build();
    }
}
