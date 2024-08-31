package com.flab.eattofit.member.ui.auth.support;

import com.flab.eattofit.helper.IntegrationHelper;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import com.flab.eattofit.member.ui.auth.support.OAuthPlatform;
import com.flab.eattofit.member.ui.auth.support.OAuthProperties;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class OAuthPropertiesTest extends IntegrationHelper {

    @Autowired
    private OAuthProperties oAuthProperties;

    @Test
    void 인증기관_이름으로_인증기관을_찾아_반환한다() {
        // given
        OAuthPlatform platform = OAuthPlatform.KAKAO;

        // when
        OAuthProviderRequest providerRequest = oAuthProperties.findByProviderName("kakao");

        // then
        assertThat(providerRequest).usingRecursiveComparison()
                .isEqualTo(oAuthProperties.getProperties().get(platform));
    }
}
