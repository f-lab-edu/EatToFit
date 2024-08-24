package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class InvalidJsonKeyException extends GlobalException {

    public InvalidJsonKeyException() {
        super(HttpStatus.BAD_REQUEST, "INVALID_JSON_KEY", "JSON 키 값이 유효하지 않습니다.");
    }
}
