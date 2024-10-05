package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.GoalNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Goal {

    INCREASE_MUSCLE("근비대"),
    MAINTAIN("유지"),
    DIET("체중 감량");

    private final String name;

    Goal(final String name) {
        this.name = name;
    }

    public static Goal findByName(final String name) {
        return Arrays.stream(values())
                .filter(goal -> goal.isSame(name))
                .findAny()
                .orElseThrow(GoalNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
