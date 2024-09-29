package com.flab.eattofit.food.ui;

import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import com.flab.eattofit.helper.IntegrationHelper;
import com.flab.eattofit.member.domain.auth.TokenManager;
import com.flab.eattofit.member.domain.member.Member;
import com.flab.eattofit.member.domain.member.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FoodControllerAcceptanceTestFixture extends IntegrationHelper {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private MemberRepository memberRepository;

    protected Member 회원_생성() {
        return memberRepository.save(Member.of("member@email.gom", "nickname"));
    }

    protected String 토큰_발급(final Member member) {
        return tokenManager.generateAccessToken(member.getId());
    }

    protected ExtractableResponse<Response> 음식_검색_요청(final String url, final String token) {
        return RestAssured.given().log().all()
                .header(AUTHORIZATION, "Bearer " + token)
                .when()
                .get(url)
                .then()
                .extract();
    }

    protected void 음식_검색_요청_결과_검증(final ExtractableResponse<Response> response) {
        PredictFoodSearchResponse result = response.as(PredictFoodSearchResponse.class);
        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(OK.value());
            softly.assertThat(result.predictFoods()).isNotEmpty();
        });
    }
}
