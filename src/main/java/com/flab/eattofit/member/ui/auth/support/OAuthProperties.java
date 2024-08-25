package com.flab.eattofit.member.ui.auth.support;

import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {

    private final Map<OAuthPlatform, OAuthProviderRequest> properties;

    public OAuthProviderRequest findByProviderName(final String name) {
        return properties.get(OAuthPlatform.findPlatform(name));
    }
}
