package com.teanlime.data.categories.model.request.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.categories.model.request.CategoriesGroup;

public class CategoriesGroupMapper implements Mapper<CategoriesGroup, String> {

    private static final String WOMEN_PATH = "women";
    private static final String MEN_PATH = "men";

    @Override
    public Optional<String> map(CategoriesGroup from) {
        if (from == null) {
            return Optional.empty();
        }
        switch (from) {
            case WOMEN:
                return Optional.of(WOMEN_PATH);
            case MEN:
                return Optional.of(MEN_PATH);
            default:
                return Optional.empty();
        }
    }
}
