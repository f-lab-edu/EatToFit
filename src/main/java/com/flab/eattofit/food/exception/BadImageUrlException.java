package com.flab.eattofit.food.exception;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class BadImageUrlException extends GlobalException {

    public BadImageUrlException() {
        super(HttpStatus.BAD_REQUEST, "BAD_IMAGE_URL", "이미지 주소가 잘못되었습니다.");
    }
}
