package com.flab.eattofit.storage.domain;

import com.flab.eattofit.storage.exception.exceptions.NotFoundExtensionTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExtensionType {

    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    private final String name;

    ExtensionType(final String name) {
        this.name = name;
    }

    public static ExtensionType findByName(final String name) {
        return Arrays.stream(values())
                .filter(extensionType -> extensionType.isSame(name))
                .findAny()
                .orElseThrow(NotFoundExtensionTypeException::new);
    }

    private boolean isSame(final String name) {
        return name.equals(this.name);
    }
}
