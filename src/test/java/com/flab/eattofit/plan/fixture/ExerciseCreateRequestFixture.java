package com.flab.eattofit.plan.fixture;

import com.flab.eattofit.plan.application.dto.ExerciseCreateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.math.BigDecimal;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ExerciseCreateRequestFixture {

    public static List<ExerciseCreateRequest> 피트니스_생성_요청_세개() {
        return List.of(
                new ExerciseCreateRequest(
                        "스쿼트",
                        10,
                        BigDecimal.valueOf(100.5),
                        12,
                        BigDecimal.valueOf(50),
                        null
                ),
                new ExerciseCreateRequest(
                        "레그 프레스",
                        8,
                        BigDecimal.valueOf(90.1),
                        10,
                        BigDecimal.valueOf(100),
                        null
                ),
                new ExerciseCreateRequest(
                        "랫 풀 다운",
                        10,
                        BigDecimal.valueOf(95.2),
                        8,
                        BigDecimal.valueOf(40),
                        null
                )
        );
    }
}
