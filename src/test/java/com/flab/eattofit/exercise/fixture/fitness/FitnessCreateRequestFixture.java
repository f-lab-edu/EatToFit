package com.flab.eattofit.exercise.fixture.fitness;

import com.flab.eattofit.exercise.application.fitness.dto.FitnessCreateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FitnessCreateRequestFixture {

    public static FitnessCreateRequest 피트니스_등록_요청서() {
        return new FitnessCreateRequest("덤벨프레스");
    }
}
