package com.flab.eattofit.profile.exception.exceptions.physicalprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class BadMemberHeightException extends GlobalException {

    public BadMemberHeightException() {
        super(HttpStatus.BAD_REQUEST, "BAD_MEMBER_HEIGHT", "가능한 회원 신장을 벗어났습니다.");
    }
}
