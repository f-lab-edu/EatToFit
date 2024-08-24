package com.flab.eattofit.global.exception;

import com.flab.eattofit.global.exception.dto.ApiErrorResponse;
import com.flab.eattofit.member.exception.exceptions.member.NicknameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public abstract class GlobalExceptionHandler {

    @ExceptionHandler(NicknameAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleNicknameAlreadyExistException(final NicknameAlreadyExistException exception) {
        return getErrorMessageWithStatus(exception.getStatus(), exception.getName(), exception.getMessage());
    }

    protected ResponseEntity<ApiErrorResponse> getErrorMessageWithStatus(final HttpStatus status,
                                                                         final String name,
                                                                         final String message) {
        return ResponseEntity.status(status)
                .body(ApiErrorResponse.of(name, message));
    }
}
