package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class MemberLoginInvalidException extends GlobalException {

    public MemberLoginInvalidException() {
        super(HttpStatus.UNAUTHORIZED, "MEMBER_LOGIN_INVALID", "로그인 정보를 찾을 수 없습니다.");
    }
}
