package com.flab.eattofit.profile.ui.physicalprofile;

import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import com.flab.eattofit.profile.application.physicalprofile.PhysicalProfileService;
import com.flab.eattofit.profile.application.physicalprofile.dto.PhysicalProfileCreateRequest;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import com.flab.eattofit.profile.ui.physicalprofile.dto.PhysicalProfileCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members/me/physical-profile")
@RestController
public class PhysicalProfileController {

    private final PhysicalProfileService physicalProfileService;

    @PostMapping
    public ResponseEntity<PhysicalProfileCreateResponse> createPhysicalProfile(
            @RequestBody @Valid final PhysicalProfileCreateRequest request,
            @AuthMember final Long memberId
    ) {
        PhysicalProfile physicalProfile = physicalProfileService.createPhysicalProfile(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PhysicalProfileCreateResponse.from(physicalProfile));
    }
}
