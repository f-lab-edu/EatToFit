package com.flab.eattofit.profile.exception.exceptions.exerciseprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class GoalNotFoundException extends GlobalException {

    public GoalNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GOAL_NOT_FOUND", "이용 가능한 목표가 아닙니다.");
    }
}
