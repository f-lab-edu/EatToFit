package com.flab.eattofit.global.exception.dto;

public record ApiErrorResponse(
        String name,
        String description
) {

    public static ApiErrorResponse of(final String name, final String description) {
        return new ApiErrorResponse(name, description);
    }
}
