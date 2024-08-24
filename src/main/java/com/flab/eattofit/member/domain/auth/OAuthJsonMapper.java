package com.flab.eattofit.member.domain.auth;

import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;
import com.flab.eattofit.member.infrastructure.auth.dto.UserInfoKeyWordRequest;

public interface OAuthJsonMapper {

    String extractValueByKey(String json, String key);
    OAuthUserResponse extractUserInfo(String infoResponse, UserInfoKeyWordRequest userInfoKeyWordRequest);
}
