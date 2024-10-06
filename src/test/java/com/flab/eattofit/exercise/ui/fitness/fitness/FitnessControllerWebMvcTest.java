package com.flab.eattofit.exercise.ui.fitness.fitness;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.exercise.application.fitness.fitness.dto.FitnessCreateRequest;
import com.flab.eattofit.exercise.domain.fitness.fitness.Fitness;
import com.flab.eattofit.helper.MockBeanInjection;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.flab.eattofit.exercise.fixture.fitness.fitness.FitnessFixture.덤벨프레스_id있음;
import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(FitnessController.class)
class FitnessControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 피트니스를_생성한다() throws Exception {
        // given
        String name = "덤벨프레스";
        FitnessCreateRequest request = new FitnessCreateRequest(name);
        Fitness fitness = 덤벨프레스_id있음();
        when(fitnessService.createFitness(request)).thenReturn(fitness);

        // when & then
        mockMvc.perform(post("/api/admin/fitness")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andExpect(status().isCreated())
            .andExpect(header().string("Location", "/fitnesses/" + fitness.getId()))
            .andDo(print())
            .andDo(customDocument("피트니스_등록",
                    requestFields(
                            fieldWithPath("name").description("등록할 피트니스 이름")
                    ),
                    responseHeaders(
                            headerWithName("Location").description("등록된 피트니스 경로 (id 포함)")
                    ),
                    responseFields(
                            fieldWithPath("name").description("등록된 피트니스 이름")
                    )
            ));
    }
}
