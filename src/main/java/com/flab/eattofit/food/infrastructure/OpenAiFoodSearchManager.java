package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.exception.BadImageUrlException;
import com.flab.eattofit.food.exception.FoodSearchJsonParseException;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
import com.flab.eattofit.global.aop.annotations.StringFile;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@Component
public class OpenAiFoodSearchManager implements FoodSearchManager {

    private static final String PROMPT_LOCATION = "prompt/predict-food.txt";

    private final ChatClient chatClient;

    @StringFile(location = PROMPT_LOCATION)
    private String prompt;

    @Override
    public PredictFoodSearchResponse search(final String url) {
        BeanOutputConverter<PredictFoodSearchResponse> converter = new BeanOutputConverter<>(PredictFoodSearchResponse.class);

        String response = chatClient.prompt()
                .user(userSpec -> generateSearchFoodPrompt(url, userSpec, converter))
                .call()
                .content();
        try {
            return converter.convert(response);
        } catch (RuntimeException exception) {
            throw new FoodSearchJsonParseException();
        }
    }

    private void generateSearchFoodPrompt(
            final String url,
            final ChatClient.PromptUserSpec userSpec,
            final BeanOutputConverter<PredictFoodSearchResponse> converter
    ) {
        try {
            userSpec.text(prompt + converter.getFormat())
                    .media(MimeTypeUtils.IMAGE_JPEG, new UrlResource(url));
        } catch (MalformedURLException e) {
            throw new BadImageUrlException();
        }
    }
}
