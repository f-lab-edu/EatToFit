package com.flab.eattofit.global.aop.exception;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends GlobalException {

    public FileNotFoundException(final String location) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_NOT_FOUND", "파일을 찾을 수 없습니다. location = " + location);
    }
}
