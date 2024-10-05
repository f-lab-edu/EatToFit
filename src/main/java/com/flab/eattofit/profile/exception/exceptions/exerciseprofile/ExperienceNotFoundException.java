package com.flab.eattofit.profile.exception.exceptions.exerciseprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class ExperienceNotFoundException extends GlobalException {

    public ExperienceNotFoundException() {
        super(HttpStatus.NOT_FOUND, "EXPERIENCE_NOT_FOUND", "이용 가능한 운동 경력이 아닙니다.");
    }
}
