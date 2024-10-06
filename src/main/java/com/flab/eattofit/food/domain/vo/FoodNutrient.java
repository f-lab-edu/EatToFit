package com.flab.eattofit.food.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Embeddable
public class FoodNutrient {

    @Column(nullable = false)
    private BigDecimal kcal;

    @Column(nullable = false)
    private BigDecimal carbohydrate;

    @Column(nullable = false)
    private BigDecimal protein;

    @Column(nullable = false)
    private BigDecimal fat;

    @Column(nullable = false)
    private BigDecimal sodium;

    public static FoodNutrient createWith(
            final BigDecimal kcal,
            final BigDecimal carbohydrate,
            final BigDecimal protein,
            final BigDecimal fat,
            final BigDecimal sodium
    ) {
        return FoodNutrient.builder()
                .kcal(kcal)
                .carbohydrate(carbohydrate)
                .protein(protein)
                .fat(fat)
                .sodium(sodium)
                .build();
    }
}
