package com.flab.eattofit.member.exception.exceptions.member;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class NicknameRandomAlgorithmException extends GlobalException {

    public NicknameRandomAlgorithmException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "NICKNAME_RANDOM_ALGORITHM_EXCEPTION", "닉네임 알고리즘 선택에서 예외가 발생했습니다.");
    }
}
