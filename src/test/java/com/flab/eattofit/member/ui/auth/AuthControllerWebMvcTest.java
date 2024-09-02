package com.flab.eattofit.member.ui.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.helper.MockBeanInjection;
import com.flab.eattofit.member.application.auth.dto.LoginRequest;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.application.auth.dto.TokenResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(AuthController.class)
class AuthControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 로그인을_진행한다() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest("kakao", "code");
        TokenResponse tokens = new TokenResponse("accessToken", "refreshToken");
        when(authService.login(any(LoginRequest.class), any(OAuthProviderRequest.class))).thenReturn(tokens);

        // when & then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest))
                ).andExpect(status().isCreated())
                .andDo(print())
                .andDo(customDocument("유저_로그인",
                        requestFields(
                                fieldWithPath("provider").description("인증기관"),
                                fieldWithPath("code").description("인증코드")
                        ),
                        responseFields(
                                fieldWithPath("accessToken").description("액세스 토큰"),
                                fieldWithPath("refreshToken").description("리프레시 토큰")
                        )
                ));

    }
}
