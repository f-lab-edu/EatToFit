package com.flab.eattofit.member.exception;

import com.flab.eattofit.global.exception.GlobalExceptionHandler;
import com.flab.eattofit.global.exception.dto.ApiErrorResponse;
import com.flab.eattofit.member.exception.exceptions.member.NicknameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(NicknameAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleNicknameAlreadyExistException(final NicknameAlreadyExistException exception) {
        return getErrorMessageWithStatus(exception, HttpStatus.BAD_REQUEST);
    }
}
