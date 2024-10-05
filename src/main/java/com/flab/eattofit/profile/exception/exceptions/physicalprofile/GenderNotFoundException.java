package com.flab.eattofit.profile.exception.exceptions.physicalprofile;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class GenderNotFoundException extends GlobalException {

    public GenderNotFoundException() {
        super(HttpStatus.NOT_FOUND, "GENDER_NOT_FOUND", "이용 가능한 성별이 아닙니다.");
    }
}
