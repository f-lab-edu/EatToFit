package com.flab.eattofit.exercise.fixture.sports;

import com.flab.eattofit.exercise.application.sports.dto.SportsCreateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class SportsCreateRequestFixture {

    public static SportsCreateRequest 스포츠_등록_요청서() {
        return new SportsCreateRequest("축구");
    }
}
