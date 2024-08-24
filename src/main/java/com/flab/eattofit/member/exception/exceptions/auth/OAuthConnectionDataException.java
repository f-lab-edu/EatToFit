package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class OAuthConnectionDataException extends GlobalException {

    public OAuthConnectionDataException() {
        super(HttpStatus.BAD_REQUEST, "OAUTH_CONNECTION_DATA_EXCEPTION", "OAuth 통신 과정에서 전달된 데이터가 잘못되었습니다.");
    }
}
