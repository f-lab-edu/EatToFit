package com.flab.eattofit.storage.domain;

import com.flab.eattofit.storage.exception.exceptions.NotFoundResourceTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResourceType {

    PROFILE("profile"),
    FOOD("food");

    private final String name;

    ResourceType(final String name) {
        this.name = name;
    }

    public static ResourceType findByName(final String name) {
        return Arrays.stream(values())
                .filter(resourceType -> resourceType.isSame(name))
                .findAny()
                .orElseThrow(NotFoundResourceTypeException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
