package com.flab.eattofit.food.domain;

import com.flab.eattofit.food.domain.vo.FoodNutrient;
import com.flab.eattofit.food.domain.vo.FoodWeight;
import com.flab.eattofit.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
public class Food extends BaseEntity {

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
