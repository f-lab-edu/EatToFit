package com.flab.eattofit.profile.ui.exerciseprofile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.helper.MockBeanInjection;
import com.flab.eattofit.profile.application.exerciseprofile.ExerciseProfileService;
import com.flab.eattofit.profile.application.exerciseprofile.dto.ExerciseProfileCreateRequest;
import com.flab.eattofit.profile.domain.exerciseprofile.ExerciseProfile;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static com.flab.eattofit.profile.fixture.exerciseprofile.ExerciseProfileFixture.운동_정보_프로필;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(ExerciseProfileController.class)
class ExerciseProfileControllerWebMvcTest extends MockBeanInjection {

    private static final String BEARER_TOKEN = "Bearer token";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ExerciseProfileService exerciseProfileService;

    @Test
    void 운동_정보_프로필을_생성한다() throws Exception {
        // given
        String experience = "1년 미만";
        String frequency = "주 4회";
        String goal = "근비대";
        String level = "초보자";
        Long memberId = 1L;

        ExerciseProfileCreateRequest request = new ExerciseProfileCreateRequest(experience, frequency, goal, level);
        ExerciseProfile exerciseProfile = 운동_정보_프로필(experience, frequency, goal, level, memberId);
        when(exerciseProfileService.createExerciseProfile(any(), any())).thenReturn(exerciseProfile);

        // when & then
        mockMvc.perform(post("/api/members/me/exercise-profile")
                        .header(AUTHORIZATION, BEARER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated())
                .andDo(print())
                .andDo(customDocument("운동_정보_프로필_생성",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("experience").description("운동 경력"),
                                fieldWithPath("frequency").description("운동 일주일 빈도"),
                                fieldWithPath("goal").description("운동 목표"),
                                fieldWithPath("level").description("운동 레벨")
                        ),
                        responseFields(
                                fieldWithPath("experience").description("운동 경력"),
                                fieldWithPath("frequency").description("운동 일주일 빈도"),
                                fieldWithPath("goal").description("운동 목표"),
                                fieldWithPath("level").description("운동 레벨"),
                                fieldWithPath("memberId").description("연관된 회원 id")
                        )
                ));
    }
}
