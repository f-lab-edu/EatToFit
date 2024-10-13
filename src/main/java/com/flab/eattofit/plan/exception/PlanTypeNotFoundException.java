package com.flab.eattofit.plan.exception;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class PlanTypeNotFoundException extends GlobalException {

    public PlanTypeNotFoundException() {
        super(HttpStatus.NOT_FOUND, "PLAN_TYPE_NOT_FOUND", "등록된 플랜 타입이 아닙니다.");
    }
}
