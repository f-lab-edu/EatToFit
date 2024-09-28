package com.flab.eattofit.exercise.exception.sports.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class SportsAlreadyExistedException extends GlobalException {

    public SportsAlreadyExistedException() {
        super(HttpStatus.BAD_REQUEST, "SPORTS_ALREADY_EXISTED", "동일한 스포츠가 이미 있습니다.");
    }
}
