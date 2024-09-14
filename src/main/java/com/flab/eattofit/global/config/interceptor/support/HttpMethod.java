package com.flab.eattofit.global.config.interceptor.support;

public enum HttpMethod {

    GET,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    HEAD,
    TRACE,
    CONNECT,
    ANY;

    public boolean matches(final String pathMethod) {
        return this == ANY ||
                this.name().equalsIgnoreCase(pathMethod);
    }
}
