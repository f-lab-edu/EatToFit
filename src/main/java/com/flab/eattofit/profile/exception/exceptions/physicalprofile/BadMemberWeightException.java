package com.flab.eattofit.profile.exception.exceptions.physicalprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class BadMemberWeightException extends GlobalException {

    public BadMemberWeightException() {
        super(HttpStatus.BAD_REQUEST, "BAD_MEMBER_WEIGHT", "가능한 회원 몸무게를 벗어났습니다.");
    }
}
