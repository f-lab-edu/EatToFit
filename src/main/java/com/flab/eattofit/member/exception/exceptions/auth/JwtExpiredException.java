package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class JwtExpiredException extends GlobalException {

    public JwtExpiredException() {
        super(HttpStatus.UNAUTHORIZED, "JWT_EXPIRED", "JWT의 기간이 만료되었습니다.");
    }
}
