package com.flab.eattofit.member.exception.exceptions.member;

public class NicknameAlreadyExistException extends RuntimeException {

    public NicknameAlreadyExistException() {
        super("이미 등록된 닉네임입니다.");
    }
}
