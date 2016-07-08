package com.teanlime.domain.api.util;

public class Validate {

    public static <T> T nonNull(T object) {
        if (object == null) {
            throw new NullPointerException("Validated object is null");
        }
        return object;
    }
}
