package com.flab.eattofit.food.fixture;

import com.flab.eattofit.food.infrastructure.dto.FoodSearchResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.math.BigDecimal;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FoodSearchResponseFixture {

    public static List<FoodSearchResponse> 음식_응답_비빔밥() {
        return List.of(
                new FoodSearchResponse(
                        "비빔밥",
                        BigDecimal.valueOf(488.02),
                        "g",
                        BigDecimal.valueOf(635.31),
                        BigDecimal.valueOf(97.13),
                        BigDecimal.valueOf(24.21),
                        BigDecimal.valueOf(16.23),
                        BigDecimal.valueOf(1248.24)
                ),
                new FoodSearchResponse(
                        "산채비빔밥",
                        BigDecimal.valueOf(433.24),
                        "g",
                        BigDecimal.valueOf(596.83),
                        BigDecimal.valueOf(93.21),
                        BigDecimal.valueOf(17.82),
                        BigDecimal.valueOf(16.23),
                        BigDecimal.valueOf(1135.35)
                ),
                new FoodSearchResponse(
                        "돌솥비빔밥",
                        BigDecimal.valueOf(430.24),
                        "g",
                        BigDecimal.valueOf(656.51),
                        BigDecimal.valueOf(93.23),
                        BigDecimal.valueOf(17.39),
                        BigDecimal.valueOf(18.66),
                        BigDecimal.valueOf(1135.35)
                )
        );
    }

    public static List<FoodSearchResponse> 음식_아님_빈_목록() {
        return List.of();
    }
}
