package com.flab.eattofit.member.exception.exceptions.auth;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class OAuthPlatformNotFoundException extends GlobalException {

    public OAuthPlatformNotFoundException() {
        super(HttpStatus.NOT_FOUND, "OAUTH_PLATFORM_NOT_FOUND", "소셜 로그인 기관을 찾을 수 없습니다.");
    }
}
