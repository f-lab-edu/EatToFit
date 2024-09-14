package com.flab.eattofit.global;

import com.flab.eattofit.global.config.interceptor.support.PathContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PathContainerTest {

    private PathContainer pathContainer;

    @BeforeEach
    void setup() {
        pathContainer = new PathContainer();
    }

    @Test
    void include로_등록한_메서드와_uri가_같으면_false를_반환한다() {
        // given
        String uri = "/uri/test";
        HttpMethod httpMethod = HttpMethod.GET;
        pathContainer.addIncludePatterns(uri, httpMethod);

        // when
        boolean result = pathContainer.isNotIncludedPath(uri, httpMethod.name());

        // then
        assertThat(result).isFalse();
    }

    @Test
    void exclude로_등록한_메서드와_uri가_같으면_true를_반환한다() {
        // given
        String uri = "/uri/test";
        HttpMethod httpMethod = HttpMethod.GET;
        pathContainer.addExcludePatterns(uri, httpMethod);

        // when
        boolean result = pathContainer.isNotIncludedPath(uri, httpMethod.name());

        // then
        assertThat(result).isTrue();
    }
}
