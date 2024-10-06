package com.flab.eattofit.exercise.exception.fitness.fitness.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FitnessNotFoundException extends GlobalException {

    public FitnessNotFoundException() {
        super(HttpStatus.NOT_FOUND, "FITNESS_NOT_FOUND", "저장되지 않은 피트니스 id가 넣어졌습니다.");
    }
}
