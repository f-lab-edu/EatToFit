package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.FrequencyNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Frequency {

    ONE("주 1회"),
    TWO("주 2회"),
    THREE("주 3회"),
    FOUR("주 4회"),
    FIVE("주 5회"),
    SIX("주 6회"),
    EVERYDAY("주 7회");

    private final String name;

    Frequency(final String name) {
        this.name = name;
    }

    public static Frequency findByName(final String name) {
        return Arrays.stream(values())
                .filter(frequency -> frequency.isSame(name))
                .findAny()
                .orElseThrow(FrequencyNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
