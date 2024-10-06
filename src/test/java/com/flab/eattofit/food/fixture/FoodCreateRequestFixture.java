package com.flab.eattofit.food.fixture;

import com.flab.eattofit.food.application.dto.FoodCreateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.math.BigDecimal;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FoodCreateRequestFixture {

    public static FoodCreateRequest 음식_생성_요청_햄버거() {
        String name = "햄버거";
        BigDecimal servingSize = BigDecimal.valueOf(150.0);
        String unit = "g";
        BigDecimal kcal = BigDecimal.valueOf(430.0);
        BigDecimal carbohydrate = BigDecimal.valueOf(36.0);
        BigDecimal protein = BigDecimal.valueOf(25.0);
        BigDecimal fat = BigDecimal.valueOf(21.0);
        BigDecimal sodium = BigDecimal.valueOf(636.0);
        String url = "burger.jpg";

        return new FoodCreateRequest(
                name,
                servingSize,
                unit,
                kcal,
                carbohydrate,
                protein,
                fat,
                sodium,
                url
        );
    }

    public static FoodCreateRequest 음식_생성_요청_햄버거_단위예외() {
        String name = "햄버거";
        BigDecimal servingSize = BigDecimal.valueOf(150.0);
        String unit = "abc";
        BigDecimal kcal = BigDecimal.valueOf(430.0);
        BigDecimal carbohydrate = BigDecimal.valueOf(36.0);
        BigDecimal protein = BigDecimal.valueOf(25.0);
        BigDecimal fat = BigDecimal.valueOf(21.0);
        BigDecimal sodium = BigDecimal.valueOf(636.0);
        String url = "burger.jpg";

        return new FoodCreateRequest(
                name,
                servingSize,
                unit,
                kcal,
                carbohydrate,
                protein,
                fat,
                sodium,
                url
        );
    }
}
