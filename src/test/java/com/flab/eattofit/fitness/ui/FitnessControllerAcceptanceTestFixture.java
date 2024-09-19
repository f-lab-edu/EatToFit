package com.flab.eattofit.fitness.ui;

import com.flab.eattofit.fitness.application.dto.FitnessCreateRequest;
import com.flab.eattofit.helper.IntegrationHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SuppressWarnings("NonAsciiCharacters")
public class FitnessControllerAcceptanceTestFixture extends IntegrationHelper {

    protected ExtractableResponse<Response> 피트니스_등록_요청(final FitnessCreateRequest request,
                                                       final String url) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    protected void 피트니스_등록_검증(final ExtractableResponse<Response> response) {
        String location = response.header(HttpHeaders.LOCATION);
        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
            softly.assertThat(location).isEqualTo("/fitness/1");
        });
    }
}
