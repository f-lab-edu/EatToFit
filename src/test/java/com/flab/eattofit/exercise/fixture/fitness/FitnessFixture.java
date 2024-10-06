package com.flab.eattofit.exercise.fixture.fitness;

import com.flab.eattofit.exercise.domain.fitness.fitness.Fitness;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FitnessFixture {

    public static Fitness 덤벨프레스_id있음() {
        return Fitness.builder()
                .id(1L)
                .name("덤벨프레스")
                .build();
    }
}
