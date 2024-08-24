package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class JsonDataInvalidException extends GlobalException {

    public JsonDataInvalidException() {
        super(HttpStatus.BAD_REQUEST, "JSON_DATA_INVALID", "유효하지 않은 JSON 데이터입니다.");
    }
}
