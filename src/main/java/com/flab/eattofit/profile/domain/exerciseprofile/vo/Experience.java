package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.ExperienceNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Experience {

    INITIAL("처음"),
    UNDER_THREE_MONTH("3개월 미만"),
    UNDER_SIX_MONTH("6개월 미만"),
    UNDER_ONE_YEAR("1년 미만"),
    OVER_ONE_YEAR("1년 이상");

    private final String name;

    Experience(final String name) {
        this.name = name;
    }

    public static Experience findByName(final String name) {
        return Arrays.stream(values())
                .filter(experience -> experience.isSame(name))
                .findAny()
                .orElseThrow(ExperienceNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
