package com.flab.eattofit.member.infrastructure.auth;

import com.flab.eattofit.member.config.auth.RestTemplateConfig;
import com.flab.eattofit.member.domain.auth.OAuthConnectionManager;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthProviderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class RestTemplateOAuthConnectionManager implements OAuthConnectionManager {

    private static final String GRANT_TYPE = "grant_type";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";

    private final RestTemplateConfig restTemplateConfig;

    @Override
    public String getOAuthAccessTokenResponse(final String code, final OAuthProviderRequest providerRequest) {
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        HttpHeaders httpHeaders = createOAuthAccessTokenRequestHeaders();
        String decode = URLDecoder.decode(code, StandardCharsets.UTF_8);
        MultiValueMap<String, String> params = createOAuthRequestBody(providerRequest, decode);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);
        return restTemplate.postForObject(providerRequest.tokenUri(), requestEntity, String.class);
    }

    private HttpHeaders createOAuthAccessTokenRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

    private MultiValueMap<String, String> createOAuthRequestBody(final OAuthProviderRequest provider, final String decode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(GRANT_TYPE, AUTHORIZATION_CODE);
        params.add(CLIENT_ID, provider.clientId());
        params.add(CLIENT_SECRET, provider.clientSecret());
        params.add(REDIRECT_URI, provider.redirectUri());
        params.add(CODE, decode);
        return params;
    }

    @Override
    public String getOAuthUserInfoResponse(final String token, final String userInfoUrl) {
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        HttpHeaders headers = createOAuthUserInfoRequestHeaders(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.postForObject(userInfoUrl, requestEntity, String.class);
    }

    private HttpHeaders createOAuthUserInfoRequestHeaders(final String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
