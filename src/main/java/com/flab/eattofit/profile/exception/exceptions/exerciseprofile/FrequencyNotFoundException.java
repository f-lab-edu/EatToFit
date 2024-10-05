package com.flab.eattofit.profile.exception.exceptions.exerciseprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FrequencyNotFoundException extends GlobalException {

    public FrequencyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "FREQUENCY_NOT_FOUND", "이용 가능한 운동 빈도가 아닙니다.");
    }
}
