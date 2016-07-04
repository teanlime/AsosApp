package com.teanlime.data.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMapper<SOURCE_MODEL, TARGET_MODEL> implements Mapper<List<SOURCE_MODEL>, List<TARGET_MODEL>> {

    private final Mapper<SOURCE_MODEL, TARGET_MODEL> mapper;

    public ListMapper(Mapper<SOURCE_MODEL, TARGET_MODEL> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TARGET_MODEL> transform(List<SOURCE_MODEL> from) {
        final List<TARGET_MODEL> to = new ArrayList<>();
        for (SOURCE_MODEL response : from) {
            to.add(mapper.transform(response));
        }
        return Collections.unmodifiableList(to);
    }
}
