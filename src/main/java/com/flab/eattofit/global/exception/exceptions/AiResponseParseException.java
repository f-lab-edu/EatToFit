package com.flab.eattofit.global.exception.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class AiResponseParseException extends GlobalException {

    public AiResponseParseException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "AI_RESPONSE_PARSE_ERROR", "AI가 응답을 반환하는 데 오류가 발생하였습니다.");
    }
}
