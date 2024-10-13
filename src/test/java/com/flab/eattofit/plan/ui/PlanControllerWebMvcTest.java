package com.flab.eattofit.plan.ui;

import com.flab.eattofit.helper.MockBeanInjection;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static com.flab.eattofit.plan.fixture.PlanSearchResponseFixture.플랜_응답_270;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(PlanController.class)
public class PlanControllerWebMvcTest extends MockBeanInjection {

    private static final String BEARER_TOKEN = "Bearer token";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 플랜을_검색한다() throws Exception {
        // given
        BigDecimal kcal = BigDecimal.valueOf(257.5);
        when(planService.planSearch(any(), any())).thenReturn(new PredictPlanSearchResponse(플랜_응답_270()));

        // when & then
        mockMvc.perform(get("/api/plans/search")
                .param("kcal", String.valueOf(kcal))
                .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("플랜_검색",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        queryParameters(
                                parameterWithName("kcal").description("음식의 칼로리")
                        ),
                        responseFields(
                                fieldWithPath("plans").description("예측된 플랜 목록"),
                                fieldWithPath("plans[].type").description("플랜의 타입"),
                                fieldWithPath("plans[].exercises").description("플랜에 속한 각 운동"),
                                fieldWithPath("plans[].exercises[].name").description("운동의 이름"),
                                fieldWithPath("plans[].exercises[].repeat").description("피트니스의 전체 세트 횟수").optional(),
                                fieldWithPath("plans[].exercises[].expect").description("각 운동의 예상 소모 칼로리"),
                                fieldWithPath("plans[].exercises[].size").description("피트니스의 각 세트 안에서의 횟수").optional(),
                                fieldWithPath("plans[].exercises[].weight").description("피트니스의 무게").optional(),
                                fieldWithPath("plans[].exercises[].time").description("스포츠의 진행 시간 (초)").optional()
                        ))
                );
    }
}
