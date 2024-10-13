package com.flab.eattofit.plan.fixture;

import com.flab.eattofit.plan.infrastructure.dto.ExerciseSearchResponse;
import com.flab.eattofit.plan.infrastructure.dto.PlanSearchResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.math.BigDecimal;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlanSearchResponseFixture {

    public static List<PlanSearchResponse> 플랜_응답_270() {
        return List.of(
                new PlanSearchResponse(
                        "피트니스",
                        List.of(
                                new ExerciseSearchResponse(
                                        "프론트 레이즈",
                                        10,
                                        BigDecimal.valueOf(120.50),
                                        4,
                                        BigDecimal.valueOf(7),
                                        null
                                ),
                                new ExerciseSearchResponse(
                                        "사이드 레터럴 레이즈",
                                        12,
                                        BigDecimal.valueOf(110.30),
                                        5,
                                        BigDecimal.valueOf(6),
                                        null
                                ),
                                new ExerciseSearchResponse(
                                        "레그 프레스",
                                        8,
                                        BigDecimal.valueOf(130),
                                        5,
                                        BigDecimal.valueOf(70),
                                        null
                                )
                        )
                ),
                new PlanSearchResponse(
                        "스포츠",
                        List.of(
                                new ExerciseSearchResponse(
                                        "축구",
                                        null,
                                        BigDecimal.valueOf(350),
                                        null,
                                        null,
                                        1800
                                )
                        )
                )
        );
    }
}
