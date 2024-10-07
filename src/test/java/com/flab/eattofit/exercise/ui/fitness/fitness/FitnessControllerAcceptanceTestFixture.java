package com.flab.eattofit.exercise.ui.fitness.fitness;

import com.flab.eattofit.exercise.application.fitness.fitness.dto.FitnessCreateRequest;
import com.flab.eattofit.helper.IntegrationHelper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;

@SuppressWarnings("NonAsciiCharacters")
public class FitnessControllerAcceptanceTestFixture extends IntegrationHelper {

    protected ExtractableResponse<Response> 피트니스_등록_요청(final FitnessCreateRequest request,
                                                       final String url) {
        return RestAssured.given().log().all()
                .contentType(JSON)
                .body(request)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    protected void 피트니스_등록_검증(final ExtractableResponse<Response> response) {
        String location = response.header(LOCATION);
        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(CREATED.value());
            softly.assertThat(location).isEqualTo("/fitness/1");
        });
    }
}
