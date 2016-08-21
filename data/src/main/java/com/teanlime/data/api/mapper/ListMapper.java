package com.teanlime.data.api.mapper;

import com.annimon.stream.Stream;
import com.teanlime.domain.api.util.streams.ImmutableCollectors;

import java.util.List;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class ListMapper<SOURCE_MODEL, TARGET_MODEL> implements Mapper<List<SOURCE_MODEL>, List<TARGET_MODEL>> {

    private final Mapper<SOURCE_MODEL, TARGET_MODEL> listItemMapper;

    public ListMapper(Mapper<SOURCE_MODEL, TARGET_MODEL> listItemMapper) {
        this.listItemMapper = nonNull(listItemMapper);
    }

    @Override
    public List<TARGET_MODEL> transform(List<SOURCE_MODEL> from) {
        return Stream.of(from)
                .map(listItemMapper::transform)
                .collect(ImmutableCollectors.toList());
    }
}
