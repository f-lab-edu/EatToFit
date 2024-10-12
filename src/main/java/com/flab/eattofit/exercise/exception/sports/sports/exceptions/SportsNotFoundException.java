package com.flab.eattofit.exercise.exception.sports.sports.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class SportsNotFoundException extends GlobalException {

    public SportsNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SPORTS_NOT_FOUND", "저장되지 않은 스포츠 id가 넣어졌습니다.");
    }
}
