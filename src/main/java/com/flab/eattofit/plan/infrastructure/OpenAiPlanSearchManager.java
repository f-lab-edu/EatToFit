package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.global.aop.annotations.StringFile;
import com.flab.eattofit.global.exception.exceptions.AiResponseParseException;
import com.flab.eattofit.plan.domain.PlanSearchManager;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchRequest;
import com.flab.eattofit.plan.infrastructure.dto.PredictPlanSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OpenAiPlanSearchManager implements PlanSearchManager {

    private static final String PLAN_SYSTEM = "prompt/predict-plan-system.txt";
    private static final String PROMPT_LOCATION = "prompt/predict-plan.txt";
    private static final String PLAN_EXAMPLE = "prompt/predict-plan-example.txt";
    private static final String KCAL = "kcal";
    private static final String EXPERIENCE = "experience";
    private static final String FREQUENCY = "frequency";
    private static final String GOAL = "goal";
    private static final String LEVEL = "level";
    private static final String BIRTH_YEAR = "birthYear";
    private static final String GENDER = "gender";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String FITNESS = "fitness";
    private static final String SPORTS = "sports";
    private static final String JOIN_MARK = ", ";

    private final ChatClient chatClient;

    @StringFile(location = PLAN_SYSTEM)
    private String system;

    @StringFile(location = PROMPT_LOCATION)
    private String prompt;

    @StringFile(location = PLAN_EXAMPLE)
    private String example;

    @Override
    public PredictPlanSearchResponse searchPlans(final PredictPlanSearchRequest request) {
        BeanOutputConverter<PredictPlanSearchResponse> converter = new BeanOutputConverter<>(PredictPlanSearchResponse.class);

        PromptTemplate promptTemplate = generatePromptTemplate(request);

        String response = chatClient.prompt()
                .system(system)
                .user(promptTemplate.render() + example + converter.getFormat())
                .call()
                .content();
        try {
            return converter.convert(response);
        } catch (RuntimeException exception) {
            throw new AiResponseParseException();
        }
    }

    private PromptTemplate generatePromptTemplate(final PredictPlanSearchRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        promptTemplate.add(KCAL, request.kcal());
        promptTemplate.add(EXPERIENCE, request.memberProfile().experience());
        promptTemplate.add(FREQUENCY, request.memberProfile().frequency());
        promptTemplate.add(GOAL, request.memberProfile().goal());
        promptTemplate.add(LEVEL, request.memberProfile().level());
        promptTemplate.add(BIRTH_YEAR, request.memberProfile().birthYear());
        promptTemplate.add(GENDER, request.memberProfile().gender());
        promptTemplate.add(WEIGHT, request.memberProfile().weight());
        promptTemplate.add(HEIGHT, request.memberProfile().height());
        promptTemplate.add(FITNESS, String.join(JOIN_MARK, request.fitness()));
        promptTemplate.add(SPORTS, String.join(JOIN_MARK, request.sports()));

        return promptTemplate;
    }
}
