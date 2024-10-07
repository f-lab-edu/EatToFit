package com.flab.eattofit.exercise.ui.fitness.memberfitness;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.exercise.application.fitness.memberfitness.dto.MemberFitnessesRequest;
import com.flab.eattofit.helper.MockBeanInjection;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.flab.eattofit.exercise.fixture.fitness.memberfitness.MemberFitnessesRequestFixture.회원_선호_피트니스_등록_두개_요청;
import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(MemberFitnessesController.class)
class MemberFitnessesControllerWebMvcTest extends MockBeanInjection {

    private static final String BEARER_TOKEN = "Bearer token";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 회원의_선호_피트니스를_등록한다() throws Exception {
        // given
        MemberFitnessesRequest request = 회원_선호_피트니스_등록_두개_요청();

        // when & then
        mockMvc.perform(post("/api/members/me/fitness/prefer-fitness")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header(AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("회원_선호_피트니스_목록_등록",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("fitnesses").description("등록할 피트니스 id 목록")
                        )
                ));
    }
}
