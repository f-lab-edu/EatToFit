package com.flab.eattofit.exercise.fixture.fitness.memberfitness;

import com.flab.eattofit.exercise.application.fitness.memberfitness.dto.MemberFitnessesRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MemberFitnessesRequestFixture {

    public static MemberFitnessesRequest 회원_선호_피트니스_등록_요청() {
        return new MemberFitnessesRequest(List.of(1L, 2L));
    }
}
