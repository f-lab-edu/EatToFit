package com.flab.eattofit.exercise.exception.fitness.fitness.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FitnessAlreadyExistedException extends GlobalException {

    public FitnessAlreadyExistedException() {
        super(HttpStatus.BAD_REQUEST, "FITNESS_ALREADY_EXISTED", "동일한 피트니스가 이미 있습니다.");
    }
}
