package com.flab.eattofit.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class GlobalException extends RuntimeException {

    private String message;
    private HttpStatus status;
    private String name;

    protected GlobalException(final HttpStatus status,
                              final String name,
                              final String message) {
        super(message);
        this.status = status;
        this.name = name;
    }
}
