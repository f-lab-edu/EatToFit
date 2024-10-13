package com.flab.eattofit.fitness.ui.fitness;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static com.flab.eattofit.fitness.fixture.fitness.FitnessCreateRequestFixture.피트니스_등록_요청서;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FitnessControllerAcceptanceTest extends FitnessControllerAcceptanceTestFixture{

    private static final String 피트니스_등록_URL = "/api/admin/fitness";

    @Test
    void 피트니스_등록() {
        // given
        var 피트니스_등록_요청서 = 피트니스_등록_요청서();

        // when
        var 피트니스_등록_요청_결과 = 피트니스_등록_요청(피트니스_등록_요청서, 피트니스_등록_URL);

        // then
        피트니스_등록_검증(피트니스_등록_요청_결과);
    }
}
