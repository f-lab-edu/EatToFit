package com.flab.eattofit.food.domain;

import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private FoodWeight weight;

    @Embedded
    private FoodNutrient nutrient;

    @Column(nullable = false)
    private Long memberId;

    private Food(final String name, final FoodWeight weight, final FoodNutrient nutrient, final Long memberId) {
        this.name = name;
        this.weight = weight;
        this.nutrient = nutrient;
        this.memberId = memberId;
    }

    public static Food createWith(
            final String name,
            final FoodWeight weight,
            final FoodNutrient nutrient,
            final Long memberId
    ) {
        return new Food(name, weight, nutrient, memberId);
    }
}
