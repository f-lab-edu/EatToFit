package com.flab.eattofit.exercise.ui.sports;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.flab.eattofit.exercise.fixture.sports.SportsCreateRequestFixture.스포츠_등록_요청서;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class SportsControllerAcceptanceTest extends SportsControllerAcceptanceTestFixture {

    private static final String 스포츠_등록_URL = "/api/admin/sports";

    @Test
    void 스포츠_등록() {
        // given
        var 스포츠_등록_요청서 = 스포츠_등록_요청서();

        // when
        var 스포츠_등록_요청_결과 = 스포츠_등록_요청(스포츠_등록_요청서, 스포츠_등록_URL);

        // then
        스포츠_등록_검증(스포츠_등록_요청_결과);
    }
}
