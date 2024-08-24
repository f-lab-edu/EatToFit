package com.flab.eattofit.member.infrastructure.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.eattofit.member.domain.auth.OAuthJsonMapper;
import com.flab.eattofit.member.exception.exceptions.auth.InvalidJsonKeyException;
import com.flab.eattofit.member.exception.exceptions.auth.JsonDataInvalidException;
import com.flab.eattofit.member.infrastructure.auth.dto.OAuthUserResponse;
import com.flab.eattofit.member.infrastructure.auth.dto.UserInfoKeyWordRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class JacksonOAuthJsonMapper implements OAuthJsonMapper {

    private static final String DELIMITER = "\\.";

    @Override
    public String extractValueByKey(final String json, final String key) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(json)
                    .get(key);
            return Optional.ofNullable(jsonNode.asText())
                    .orElseThrow(InvalidJsonKeyException::new);
        } catch (JsonProcessingException exception) {
            throw new JsonDataInvalidException();
        }
    }

    @Override
    public OAuthUserResponse extractUserInfo(final String infoResponse, final UserInfoKeyWordRequest userInfoKeyWordRequest) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(infoResponse);
            String email = getValue(userInfoKeyWordRequest.emailKeyWord(), jsonNode);
            return new OAuthUserResponse(email);

        } catch (JsonProcessingException exception) {
            throw new JsonDataInvalidException();
        }
    }

    private String getValue(final String keyWord, final JsonNode jsonNode) {
        return Arrays.stream(keyWord.split(DELIMITER))
                .reduce(jsonNode, JsonNode::get, (parentPath, childPath) -> childPath)
                .asText();
    }
}
