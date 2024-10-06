package com.flab.eattofit.global.aop.exception;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FieldAccessException extends GlobalException {

    public FieldAccessException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "FIELD_ACCESS_ERROR", "필드 주입에 실패했습니다.");
    }
}
