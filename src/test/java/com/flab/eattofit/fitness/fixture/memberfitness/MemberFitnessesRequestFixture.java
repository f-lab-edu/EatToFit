package com.flab.eattofit.fitness.fixture.memberfitness;

import com.flab.eattofit.fitness.application.memberfitness.dto.MemberFitnessesRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MemberFitnessesRequestFixture {

    public static MemberFitnessesRequest 회원_선호_피트니스_등록_두개_요청() {
        return new MemberFitnessesRequest(List.of(1L, 2L));
    }
}
