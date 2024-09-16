package com.flab.eattofit.storage.ui;

import com.flab.eattofit.helper.MockBeanInjection;
import com.flab.eattofit.storage.infrastructure.dto.PresignedUrlResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.flab.eattofit.helper.RestDocsHelper.customDocument;
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
@WebMvcTest(StorageController.class)
class StorageControllerWebMvcTest extends MockBeanInjection {

    private static final String BEARER_TOKEN = "Bearer token";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void presigned_url과_파일_저장_경로를_요청한다() throws Exception {
        // given
        String resource = "food";
        String name = "hamburger.jpg";
        String presignedUrl = "http://storage.com/food/hamburger-1.jpg?env=test...";
        String fileUrl = "http://storage.com/food/hamburger-1.jpg";

        when(storageService.getPresignedUrl(any(), any(), any()))
                .thenReturn(new PresignedUrlResponse(presignedUrl, fileUrl));

        // when & then
        mockMvc.perform(get("/api/storage/presigned-url")
                        .header(AUTHORIZATION, BEARER_TOKEN)
                        .param("resource", resource)
                        .param("name", name))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("스토리지_경로_요청",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        queryParameters(
                            parameterWithName("resource").description("리소스 종류 (food, profile)"),
                                parameterWithName("name").description("파일 이름")
                        ),
                        responseFields(
                                fieldWithPath("presignedUrl").description("Presigned URL"),
                                fieldWithPath("fileUrl").description("File URL")
                        )
                ));
    }
}
