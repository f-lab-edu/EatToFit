package com.flab.eattofit.sports.ui.sports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.sports.application.sports.dto.SportsCreateRequest;
import com.flab.eattofit.sports.domain.sports.Sports;
import com.flab.eattofit.helper.MockBeanInjection;
import com.flab.eattofit.sports.ui.sports.SportsController;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.flab.eattofit.sports.fixture.sports.SportsFixture.축구_id있음;
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
@WebMvcTest(SportsController.class)
class SportsControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 스포츠를_생성한다() throws Exception {
        // given
        String name = "축구";
        SportsCreateRequest request = new SportsCreateRequest(name);
        Sports sports = 축구_id있음();
        when(sportsService.createSports(request)).thenReturn(sports);

        // when & then
        mockMvc.perform(post("/api/admin/sports")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated())
                .andExpect(header().string("Location", "/sports/" + sports.getId()))
                .andDo(print())
                .andDo(customDocument("스포츠_등록",
                        requestFields(
                                fieldWithPath("name").description("등록할 스포츠 이름")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("등록된 스포츠 경로 (id 포함)")
                        ),
                        responseFields(
                                fieldWithPath("name").description("등록된 스포츠 이름")
                        )
                ));
    }
}
