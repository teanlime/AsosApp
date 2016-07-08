package com.teanlime.data.categories.model.request.mapper;

import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.categories.model.request.CategoriesGroup;

public class CategoriesGroupMapper implements Mapper<CategoriesGroup, String> {

    private static final String WOMEN_PATH = "women";
    private static final String MEN_PATH = "men";

    @Override
    public String transform(CategoriesGroup from) {
        if (from == null) {
            return WOMEN_PATH;
        }
        switch (from) {
            case WOMEN:
                return WOMEN_PATH;
            case MEN:
                return MEN_PATH;
            default:
                return WOMEN_PATH;
        }
    }
}
