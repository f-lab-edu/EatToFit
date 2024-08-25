package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.domain.member.NicknameGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomNicknameGenerator implements NicknameGenerator {

    private static final int RANDOM_LENGTH = 5;
    private static final int DIGIT = 10;

    @Override
    public String generateNickname(final String originName) {
        Random random = new Random();

        String generated = IntStream.range(0, RANDOM_LENGTH)
                .mapToObj(value -> String.valueOf(random.nextInt(DIGIT)))
                .collect(Collectors.joining());

        return originName + generated;
    }
}
