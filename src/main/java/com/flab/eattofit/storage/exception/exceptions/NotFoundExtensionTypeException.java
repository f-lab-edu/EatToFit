package com.flab.eattofit.storage.exception.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class NotFoundExtensionTypeException extends GlobalException {

    public NotFoundExtensionTypeException() {
        super(HttpStatus.NOT_FOUND, "NOT_FOUND_EXTENSION_TYPE", "등록되지 않은 확장자 타입입니다.");
    }
}
