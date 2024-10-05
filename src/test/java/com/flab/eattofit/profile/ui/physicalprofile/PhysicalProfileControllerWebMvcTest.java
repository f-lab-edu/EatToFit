package com.flab.eattofit.profile.ui.physicalprofile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.helper.MockBeanInjection;
import com.flab.eattofit.profile.application.physicalprofile.dto.PhysicalProfileCreateRequest;
import com.flab.eattofit.profile.domain.physicalprofile.PhysicalProfile;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static com.flab.eattofit.profile.fixture.physicalprofile.PhysicalProfileFixture.신체_정보_프로필;
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
@WebMvcTest(PhysicalProfileController.class)
class PhysicalProfileControllerWebMvcTest extends MockBeanInjection {

    private static final String BEARER_TOKEN = "Bearer token";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 신체_정보_프로필을_생성한다() throws Exception {
        // given
        int birthYear = 2000;
        String gender = "남성";
        BigDecimal weight = BigDecimal.valueOf(67.2);
        BigDecimal height = BigDecimal.valueOf(172.2);
        PhysicalProfileCreateRequest request = new PhysicalProfileCreateRequest(birthYear, gender, weight, height);
        Long memberId = 1L;

        PhysicalProfile physicalProfile = 신체_정보_프로필(birthYear, gender, weight, height, memberId);
        when(physicalProfileService.createPhysicalProfile(any(), any())).thenReturn(physicalProfile);

        // when & then
        mockMvc.perform(post("/api/members/me/physical-profile")
                        .header(AUTHORIZATION, BEARER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated())
                .andDo(print())
                .andDo(customDocument("신체_정보_프로필_생성",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("birthYear").description("출생 년도"),
                                fieldWithPath("gender").description("성별"),
                                fieldWithPath("weight").description("몸무게 (kg)"),
                                fieldWithPath("height").description("신장 (cm)")
                        ),
                        responseFields(
                                fieldWithPath("birthYear").description("출생 년도"),
                                fieldWithPath("gender").description("성별"),
                                fieldWithPath("weight").description("몸무게 (kg)"),
                                fieldWithPath("height").description("신장 (cm)"),
                                fieldWithPath("memberId").description("연관된 회원 id")
                        )
                ));
    }
}
