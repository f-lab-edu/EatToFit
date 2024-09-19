package com.flab.eattofit.storage.application;

import com.flab.eattofit.storage.exception.exceptions.BadFileNameException;
import com.flab.eattofit.storage.exception.exceptions.NotFoundExtensionTypeException;
import com.flab.eattofit.storage.exception.exceptions.NotFoundResourceTypeException;
import com.flab.eattofit.storage.infrastructure.FakeStorageManager;
import com.flab.eattofit.storage.infrastructure.dto.PresignedUrlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class StorageServiceTest {

    private StorageService storageService;

    @BeforeEach
    void init() {
        storageService = new StorageService(new FakeStorageManager());
    }

    @Test
    void presigned_url과_파일_저장_경로를_받는다() {
        // given
        Long memberId = 1L;
        String resource = "profile";
        String name = "test.jpg";

        // when
        PresignedUrlResponse response = storageService.getPresignedUrl(memberId, resource, name);

        // then
        assertSoftly(softly -> {
            softly.assertThat(response.presignedUrl()).isNotEmpty();
            softly.assertThat(response.fileUrl()).isNotEmpty();
        });
    }

    @Test
    void 가능한_자원_타입이_아닐_경우_예외가_발생한다() {
        // given
        Long memberId = 1L;
        String resource = "abc";
        String name = "test.jpg";

        // when & then
        assertThatThrownBy(() -> storageService.getPresignedUrl(memberId, resource, name))
                .isInstanceOf(NotFoundResourceTypeException.class);
    }

    @Test
    void 가능한_확장자_타입이_아닐_경우_예외가_발생한다() {
        // given
        Long memberId = 1L;
        String resource = "profile";
        String name = ".hello";

        // when & then
        assertThatThrownBy(() -> storageService.getPresignedUrl(memberId, resource, name))
                .isInstanceOf(NotFoundExtensionTypeException.class);
    }

    @Test
    void 확장자_구분이_없을_경우_예외가_발생한다() {
        // given
        Long memberId = 1L;
        String resource = "profile";
        String name = "hello";

        // when & then
        assertThatThrownBy(() -> storageService.getPresignedUrl(memberId, resource, name))
                .isInstanceOf(BadFileNameException.class);
    }
}
