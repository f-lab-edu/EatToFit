package com.flab.eattofit.food.domain.vo;

import com.flab.eattofit.food.exception.FoodUnitNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FoodUnit {
    GRAM("g"),
    KILOGRAM("kg");

    private final String name;

    FoodUnit(final String name) {
        this.name = name;
    }

    public static FoodUnit findByName(final String name) {
        return Arrays.stream(values())
                .filter(unit -> unit.isSame(name))
                .findAny()
                .orElseThrow(FoodUnitNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
