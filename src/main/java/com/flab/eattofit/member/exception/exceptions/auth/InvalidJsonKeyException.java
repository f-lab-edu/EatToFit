package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class InvalidJsonKeyException extends GlobalException {

    public InvalidJsonKeyException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "INVALID_JSON_KEY", "서버에서 JSON 키를 찾지 못했습니다.");
    }
}
