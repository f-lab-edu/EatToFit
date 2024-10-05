package com.flab.eattofit.profile.exception.exceptions.exerciseprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class AlreadyCreatedExerciseProfileException extends GlobalException {

    public AlreadyCreatedExerciseProfileException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_CREATED_EXERCISE_PROFILE", "이미 회원의 운동 프로필 정보를 등록했습니다.");
    }
}
