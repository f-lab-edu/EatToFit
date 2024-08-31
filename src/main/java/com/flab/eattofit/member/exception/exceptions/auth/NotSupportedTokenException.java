package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class NotSupportedTokenException extends GlobalException {

    public NotSupportedTokenException() {
        super(HttpStatus.UNAUTHORIZED, "NOT_SUPPORTED_TOKEN", "지원되지 않는 토큰입니다.");
    }
}
