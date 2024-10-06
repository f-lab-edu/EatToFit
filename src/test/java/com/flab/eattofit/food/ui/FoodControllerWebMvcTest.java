package com.flab.eattofit.food.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.food.application.dto.FoodCreateRequest;
import com.flab.eattofit.food.domain.Food;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import com.flab.eattofit.helper.MockBeanInjection;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import static com.flab.eattofit.food.fixture.FoodFixture.음식_생성_응답_id있음;
import static com.flab.eattofit.food.fixture.FoodSearchResponseFixture.음식_응답_비빔밥;
import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(FoodController.class)
class FoodControllerWebMvcTest extends MockBeanInjection {

    private static final String BEARER_TOKEN = "Bearer token";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 음식을_생성한다() throws Exception {
        // given
        String name = "햄버거";
        BigDecimal servingSize = BigDecimal.valueOf(150.0);
        String unit = "g";
        BigDecimal kcal = BigDecimal.valueOf(430.0);
        BigDecimal carbohydrate = BigDecimal.valueOf(36.0);
        BigDecimal protein = BigDecimal.valueOf(25.0);
        BigDecimal fat = BigDecimal.valueOf(21.0);
        BigDecimal sodium = BigDecimal.valueOf(636.0);
        String url = "burger.jpg";

        FoodCreateRequest request = new FoodCreateRequest(
                name,
                servingSize,
                unit,
                kcal,
                carbohydrate,
                protein,
                fat,
                sodium,
                url
        );
        Long memberId = 1L;
        Food food = 음식_생성_응답_id있음(request, memberId);
        when(foodService.createFood(any(), any())).thenReturn(food);

        // when & then
        mockMvc.perform(post("/api/foods")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/foods/" + food.getId()))
                .andDo(print())
                .andDo(customDocument("음식 생성",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("name").description("음식 이름"),
                                fieldWithPath("servingSize").description("음식 사이즈"),
                                fieldWithPath("unit").description("음식 사이즈 단위"),
                                fieldWithPath("kcal").description("음식 칼로리"),
                                fieldWithPath("carbohydrate").description("음식 탄수화물"),
                                fieldWithPath("protein").description("음식 단백질"),
                                fieldWithPath("fat").description("음식 지방"),
                                fieldWithPath("sodium").description("음식 나트륨"),
                                fieldWithPath("url").description("음식 이미지 주소")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("등록된 음식 경로 (id 포함)")
                        ),
                        responseFields(
                                fieldWithPath("name").description("음식 이름"),
                                fieldWithPath("servingSize").description("음식 사이즈"),
                                fieldWithPath("unit").description("음식 사이즈 단위"),
                                fieldWithPath("kcal").description("음식 칼로리"),
                                fieldWithPath("carbohydrate").description("음식 탄수화물"),
                                fieldWithPath("protein").description("음식 단백질"),
                                fieldWithPath("fat").description("음식 지방"),
                                fieldWithPath("sodium").description("음식 나트륨"),
                                fieldWithPath("memberId").description("생성한 회원 id"),
                                fieldWithPath("url").description("음식 이미지 주소"),
                                fieldWithPath("createdAt").description("음식 생성 시각")
                        )
                ));
    }

    @Test
    void 음식을_검색한다() throws Exception {
        // given
        String url = "www.food.com/bibimbap.jpg";
        when(foodService.foodSearch(any(), any())).thenReturn(new PredictFoodSearchResponse(음식_응답_비빔밥()));

        // when & then
        mockMvc.perform(get("/api/foods/search")
                        .param("image_url", url)
                        .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("음식_검색",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        queryParameters(
                                parameterWithName("image_url").description("음식 사진이 위치한 이미지 주소")
                        ),
                        responseFields(
                                fieldWithPath("predictFoods").description("예측된 음식 목록"),
                                fieldWithPath("predictFoods[].name").description("음식 이름"),
                                fieldWithPath("predictFoods[].servingSize").description("음식 무게 (unit 기준)"),
                                fieldWithPath("predictFoods[].unit").description("음식 무게 단위 (g, kg)"),
                                fieldWithPath("predictFoods[].kcal").description("음식 칼로리"),
                                fieldWithPath("predictFoods[].carbohydrate").description("음식 탄수화물"),
                                fieldWithPath("predictFoods[].protein").description("음식 단백질"),
                                fieldWithPath("predictFoods[].fat").description("음식 지방"),
                                fieldWithPath("predictFoods[].sodium").description("음식 나트륨")
                        )
                ));
    }
}
