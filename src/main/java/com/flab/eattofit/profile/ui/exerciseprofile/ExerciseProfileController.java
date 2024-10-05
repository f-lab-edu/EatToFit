package com.flab.eattofit.profile.ui.exerciseprofile;

import com.flab.eattofit.member.ui.auth.support.annotations.AuthMember;
import com.flab.eattofit.profile.application.exerciseprofile.ExerciseProfileService;
import com.flab.eattofit.profile.application.exerciseprofile.dto.ExerciseProfileCreateRequest;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import com.flab.eattofit.profile.ui.exerciseprofile.dto.ExerciseProfileCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members/me/exercise-profile")
@RestController
public class ExerciseProfileController {

    private final ExerciseProfileService exerciseProfileService;

    @PostMapping
    public ResponseEntity<ExerciseProfileCreateResponse> createExerciseProfile(
            @RequestBody @Valid final ExerciseProfileCreateRequest request,
            @AuthMember final Long memberId
    ) {
        ExerciseProfile exerciseProfile = exerciseProfileService.createExerciseProfile(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ExerciseProfileCreateResponse.createWith(exerciseProfile));
    }
}
