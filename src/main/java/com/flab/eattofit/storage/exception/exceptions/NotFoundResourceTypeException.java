package com.flab.eattofit.storage.exception.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class NotFoundResourceTypeException extends GlobalException {

    public NotFoundResourceTypeException() {
        super(HttpStatus.NOT_FOUND, "NOT_FOUND_RESOURCE_TYPE", "등록되지 않은 리소스명 입니다.");
    }
}
