package com.flab.eattofit.plan.fixture;

import com.flab.eattofit.plan.application.dto.PlanCreateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import static com.flab.eattofit.plan.fixture.ExerciseCreateRequestFixture.피트니스_생성_요청_세개;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlanCreateRequestFixture {

    public static PlanCreateRequest 플랜_생성_요청_피트니스_세개() {
        return new PlanCreateRequest("피트니스", 피트니스_생성_요청_세개());
    }

    public static PlanCreateRequest 플랜_생성_요청_없는_타입() {
        return new PlanCreateRequest("abc", 피트니스_생성_요청_세개());
    }
}
