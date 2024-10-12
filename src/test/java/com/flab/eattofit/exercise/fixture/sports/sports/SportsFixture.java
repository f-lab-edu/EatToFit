package com.flab.eattofit.exercise.fixture.sports.sports;

import com.flab.eattofit.exercise.domain.sports.sports.Sports;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class SportsFixture {

    public static Sports 축구_id있음() {
        return Sports.builder()
                .id(1L)
                .name("축구")
                .build();
    }
}
