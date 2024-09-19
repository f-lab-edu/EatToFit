package com.flab.eattofit.storage.exception.exceptions;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class BadFileNameException extends GlobalException {

    public BadFileNameException() {
        super(HttpStatus.BAD_REQUEST, "BAD_FILE_NAME", "확장자명이 없습니다.");
    }
}
