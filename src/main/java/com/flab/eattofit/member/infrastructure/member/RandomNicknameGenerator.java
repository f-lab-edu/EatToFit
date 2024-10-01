package com.flab.eattofit.member.infrastructure.member;

import com.flab.eattofit.member.domain.member.NicknameGenerator;
import com.flab.eattofit.member.exception.exceptions.member.NicknameRandomAlgorithmException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomNicknameGenerator implements NicknameGenerator {

    private static final int RANDOM_LENGTH = 5;
    private static final int DIGIT = 10;

    @Override
    public String generateNickname(final String originName) {
        try {
            SecureRandom finalSecureRandom = SecureRandom.getInstanceStrong();
            String generated = IntStream.range(0, RANDOM_LENGTH)
                    .mapToObj(value -> String.valueOf(finalSecureRandom.nextInt(DIGIT)))
                    .collect(Collectors.joining());
            return originName + generated;
        } catch (NoSuchAlgorithmException exception) {
            throw new NicknameRandomAlgorithmException();
        }
    }
}
