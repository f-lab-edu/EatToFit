package com.flab.eattofit.food.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FoodWeight {

    @Column(nullable = false)
    private BigDecimal servingSize;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FoodUnit unit;

    public static FoodWeight createWith(final BigDecimal servingSize, final String unit) {
        return new FoodWeight(servingSize, FoodUnit.findByName(unit));
    }
}
