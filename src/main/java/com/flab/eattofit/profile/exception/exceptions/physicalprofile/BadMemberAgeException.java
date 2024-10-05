package com.flab.eattofit.profile.exception.exceptions.physicalprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class BadMemberAgeException extends GlobalException {

    public BadMemberAgeException() {
        super(HttpStatus.BAD_REQUEST, "BAD_MEMBER_AGE", "가능한 회원 나이를 벗어났습니다.");
    }
}
