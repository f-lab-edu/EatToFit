package com.flab.eattofit.member.ui.auth.support.resolver;

import com.flab.eattofit.member.domain.auth.OAuthJsonMapper;
import com.flab.eattofit.member.ui.auth.support.OAuthProperties;
import com.flab.eattofit.member.ui.auth.support.annotations.OAuthAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class OAuthArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String KEY = "provider";

    private final OAuthProperties properties;
    private final OAuthJsonMapper jsonMapper;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(OAuthAuthority.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {
        ContentCachingRequestWrapper request = webRequest.getNativeRequest(ContentCachingRequestWrapper.class);
        String requestBody = Objects.requireNonNull(request).getContentAsString();
        String provider = jsonMapper.extractValueByKey(requestBody, KEY);

        return properties.findByProviderName(provider);
    }
}
