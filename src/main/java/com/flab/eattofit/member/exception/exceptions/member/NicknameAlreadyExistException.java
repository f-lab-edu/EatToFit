package com.flab.eattofit.member.exception.exceptions.member;

import com.flab.eattofit.global.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class NicknameAlreadyExistException extends GlobalException {

    public NicknameAlreadyExistException() {
        super(HttpStatus.BAD_REQUEST, "NICKNAME_ALREADY_EXIST", "이미 등록된 닉네임입니다.");
    }
}
