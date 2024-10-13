package com.flab.eattofit.sports.ui.sports;

import com.flab.eattofit.sports.application.sports.dto.SportsCreateRequest;
import com.flab.eattofit.helper.IntegrationHelper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;

@SuppressWarnings("NonAsciiCharacters")
public class SportsControllerAcceptanceTestFixture extends IntegrationHelper {

    protected ExtractableResponse<Response> 스포츠_등록_요청(final SportsCreateRequest request, final String url) {
        return RestAssured.given().log().all()
                .contentType(JSON)
                .body(request)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    protected void 스포츠_등록_검증(final ExtractableResponse<Response> response) {
        String location = response.header(LOCATION);
        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(CREATED.value());
            softly.assertThat(location).isEqualTo("/sports/1");
        });
    }
}
