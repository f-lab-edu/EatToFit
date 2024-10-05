package com.flab.eattofit.profile.exception.exceptions.exerciseprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class LevelNotFoundException extends GlobalException {

    public LevelNotFoundException() {
        super(HttpStatus.NOT_FOUND, "LEVEL_NOT_FOUND", "이용 가능한 레벨이 아닙니다.");
    }
}
