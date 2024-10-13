package com.flab.eattofit.plan.domain.vo;

import com.flab.eattofit.plan.exception.PlanTypeNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PlanType {

    FITNESS("피트니스"),
    SPORTS("스포츠");

    private final String value;

    PlanType(final String value) {
        this.value = value;
    }

    public static PlanType findByName(final String name) {
        return Arrays.stream(values())
                .filter(type -> type.isSame(name))
                .findAny()
                .orElseThrow(PlanTypeNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.value);
    }
}
