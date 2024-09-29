package com.flab.eattofit.food.ui;

import com.flab.eattofit.food.application.FoodService;
import com.flab.eattofit.food.fixture.FoodSearchResponseFixture;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import com.flab.eattofit.member.domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.flab.eattofit.food.fixture.FoodSearchResponseFixture.음식_응답_비빔밥;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class FoodControllerAcceptanceTest extends FoodControllerAcceptanceTestFixture {

    private static final String 음식_검색_URL = "/api/foods/search?image_url=www.food.com/bibimbap.jpg";

    @MockBean
    private FoodService foodService;

    private String 토큰;
    private Member 회원;

    @BeforeEach
    void init() {
        회원 = 회원_생성();
        토큰 = 토큰_발급(회원);
    }

    @Test
    void 음식_검색() {
        // given
        var 음식_검색_목록 = new PredictFoodSearchResponse(음식_응답_비빔밥());
        when(foodService.foodSearch(any(), any())).thenReturn(음식_검색_목록);

        // when
        var 음식_검색_요청_결과 = 음식_검색_요청(음식_검색_URL, 토큰);

        // then
        음식_검색_요청_결과_검증(음식_검색_요청_결과);
    }
}
