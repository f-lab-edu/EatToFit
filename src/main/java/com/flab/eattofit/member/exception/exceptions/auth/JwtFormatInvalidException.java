package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class JwtFormatInvalidException extends GlobalException {

    public JwtFormatInvalidException() {
        super(HttpStatus.UNAUTHORIZED, "JWT_FORMAT_INVALID", "JWT의 토큰 형식, 길이가 올바르지 않습니다.");
    }
}
