package com.flab.eattofit.profile.domain.exerciseprofile.vo;

import com.flab.eattofit.profile.exception.exceptions.exerciseprofile.LevelNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Level {

    NOT_EXPERIENCED("입문자"),
    NEWBIE("초보자"),
    INTERMEDIATE("중급자"),
    EXPERT("전문가");

    private final String name;

    Level(final String name) {
        this.name = name;
    }

    public static Level findByName(final String name) {
        return Arrays.stream(values())
                .filter(level -> level.isSame(name))
                .findAny()
                .orElseThrow(LevelNotFoundException::new);
    }
    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
