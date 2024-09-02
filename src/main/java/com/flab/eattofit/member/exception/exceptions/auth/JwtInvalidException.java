package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class JwtInvalidException extends GlobalException {

    public JwtInvalidException() {
        super(HttpStatus.UNAUTHORIZED, "JWT_INVALID_EXCEPTION", "JWT 해석 과정에서 예외가 발생했습니다.");
    }
}
