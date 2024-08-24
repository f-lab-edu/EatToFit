package com.flab.eattofit.global.exception.dto;

public record ApiErrorResponse(
        String name,
        String message
) {

    public static ApiErrorResponse of(final String name, final String message) {
        return new ApiErrorResponse(name, message);
    }
}
