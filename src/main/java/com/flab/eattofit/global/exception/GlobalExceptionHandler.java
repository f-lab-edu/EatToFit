package com.flab.eattofit.global.exception;

import com.flab.eattofit.global.exception.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String METHOD_ARGUMENT_NOT_VALID = "METHOD_ARGUMENT_NOT_VALID";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return getErrorMessageWithStatus(HttpStatus.BAD_REQUEST, METHOD_ARGUMENT_NOT_VALID, message);
    }

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
