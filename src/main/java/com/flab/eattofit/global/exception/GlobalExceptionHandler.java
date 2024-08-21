package com.flab.eattofit.global.exception;

import com.flab.eattofit.global.exception.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class GlobalExceptionHandler {

    public ResponseEntity<ApiErrorResponse> getErrorMessageWithStatus(final Exception exception, final HttpStatus status) {
        String exceptionName = exception.getClass()
                .getName();
        return ResponseEntity.status(status)
                .body(ApiErrorResponse.of(exceptionName, exception.getMessage()));
    }
}
