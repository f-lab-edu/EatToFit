package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class JwtSignVerifyException extends GlobalException {

    public JwtSignVerifyException() {
        super(HttpStatus.UNAUTHORIZED, "JWT_SIGN_NOT_FOUND", "JWT 서명을 확인하지 못했습니다.");
    }
}
