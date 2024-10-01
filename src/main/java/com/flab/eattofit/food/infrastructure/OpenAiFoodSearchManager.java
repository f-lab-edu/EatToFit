package com.flab.eattofit.food.infrastructure;

import com.flab.eattofit.food.domain.FoodSearchManager;
import com.flab.eattofit.food.exception.BadImageUrlException;
import com.flab.eattofit.food.exception.FoodSearchJsonParseException;
import com.flab.eattofit.food.infrastructure.dto.PredictFoodSearchResponse;
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

    private final ChatClient chatClient;

    @Override
    public PredictFoodSearchResponse search(final String url) {
        BeanOutputConverter<PredictFoodSearchResponse> converter = new BeanOutputConverter<>(PredictFoodSearchResponse.class);

        String response = chatClient.prompt()
                .user(userSpec -> {
                    try {
                        generateSearchFoodPrompt(url, userSpec, converter);
                    } catch (MalformedURLException exception) {
                        throw new BadImageUrlException();
                    }
                })
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
    ) throws MalformedURLException {
        userSpec.text(
                """
                <role>
                너는 20년 이상 음식 경력을 가진 요리사 겸 영양사다. 음식 사진을 주면, 제공된 format에 맞춰 응답해야 한다.
                </role>
                <instruction>
                1. 예측 가능한 음식은 최소 3개 이상 나와야 한다.
                2. unit은 'g'로 고정한다.
                3. servingSize는 gram 기준으로 계산한다.
                4. servingSize, kcal, carbohydrate, protein, fat, sodium은 모두 소수점 둘째 자리까지 표현되어야 한다.
                5. 소수점 둘째 자리들로 표현을 할 때, 이것들이 모두 0으로 된 경우가 아닌 경우도 있을 수 있다. (0으로 된 경우가 있을 수도 있다.)
                6. name은 한글 기준으로 나와야 한다.
                7. 만약 음식이 아니라면, predictFoods를 빈 배열로 반환하라.
                </instruction>
                <example>
                예시 응답을 알려주겠다. 예시 응답과 같은 음식이 나오더라도 이 예시 값을 똑같이 활용하지는 마라.
                {
                    "predictFoods": [
                        {
                            "name": "비빔밥",
                            "servingSize": 488.02,
                            "unit": "g",
                            "kcal": 635.31,
                            "carbohydrate": 97.13,
                            "protein": 24.21,
                            "fat": 16.23,
                            "sodium": 1248.24
                        },
                        {
                            "name": "산채비빔밥",
                            "servingSize": 433.24,
                            "unit": "g",
                            "kcal": 596.83,
                            "carbohydrate": 93.21,
                            "protein": 17.82,
                            "fat": 16.23,
                            "sodium": 1135.35
                        },
                        {
                            "name": "돌솥비빔밥",
                            "servingSize": 430.24,
                            "unit": "g",
                            "kcal": 656.51,
                            "carbohydrate": 93.23,
                            "protein": 17.39,
                            "fat": 18.66,
                            "sodium": 1135.35
                        }
                    ]
                }
                </example>
                format은 아래와 같다.
                """ + converter.getFormat())
                .media(MimeTypeUtils.IMAGE_JPEG, new UrlResource(url));
    }
}
