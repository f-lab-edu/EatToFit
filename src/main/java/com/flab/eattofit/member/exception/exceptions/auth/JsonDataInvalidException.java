package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class JsonDataInvalidException extends GlobalException {

    public JsonDataInvalidException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "JSON_DATA_INVALID", "JSON 해석 과정에서 문제가 발생했습니다.");
    }
}
