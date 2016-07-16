package com.teanlime.data.api.mapper;

import com.annimon.stream.Stream;
import com.teanlime.domain.api.util.streams.ImmutableCollectors;

import java.util.List;

public class ListMapper<SOURCE_MODEL, TARGET_MODEL> implements Mapper<List<SOURCE_MODEL>, List<TARGET_MODEL>> {

    private final Mapper<SOURCE_MODEL, TARGET_MODEL> mapper;

    public ListMapper(Mapper<SOURCE_MODEL, TARGET_MODEL> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TARGET_MODEL> transform(List<SOURCE_MODEL> from) {
        return Stream.of(from)
                .map(mapper::transform)
                .collect(ImmutableCollectors.toList());
    }
}
