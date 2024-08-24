package com.flab.eattofit.global.exception;

import com.flab.eattofit.global.exception.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(final GlobalException exception) {
        return getErrorMessageWithStatus(exception.getStatus(), exception.getName(), exception.getMessage());
    }

    protected ResponseEntity<ApiErrorResponse> getErrorMessageWithStatus(final HttpStatus status,
                                                                         final String name,
                                                                         final String message) {
        return ResponseEntity.status(status)
                .body(ApiErrorResponse.of(name, message));
    }
}
