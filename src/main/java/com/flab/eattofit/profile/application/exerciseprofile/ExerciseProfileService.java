package com.flab.eattofit.profile.application.exerciseprofile;

import com.flab.eattofit.profile.application.exerciseprofile.dto.ExerciseProfileCreateRequest;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfileRepository;
import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.AlreadyCreatedExerciseProfileException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ExerciseProfileService {

    private final ExerciseProfileRepository exerciseProfileRepository;

    public ExerciseProfile createExerciseProfile(final ExerciseProfileCreateRequest request, final Long memberId) {
        if (exerciseProfileRepository.isExistedByMemberId(memberId)) {
            throw new AlreadyCreatedExerciseProfileException();
        }
        ExerciseProfile exerciseProfile = ExerciseProfile.createWith(request.experience(), request.frequency(), request.goal(), request.level(), memberId);
        return exerciseProfileRepository.save(exerciseProfile);
    }
}
