package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.teanlime.domain.api.util.streams.ImmutableCollectors;

import java.util.List;

import javax.inject.Inject;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Converts a list of objects of a given type to a list of objects or another type
 * This mapper removes null items
 *
 * @param <SOURCE_MODEL> the type of the original list
 * @param <TARGET_MODEL> the type for the new converted list
 */
public class ListMapper<SOURCE_MODEL, TARGET_MODEL> implements Mapper<List<SOURCE_MODEL>, List<TARGET_MODEL>> {

    private final Mapper<SOURCE_MODEL, TARGET_MODEL> listItemMapper;

    @Inject
    public ListMapper(Mapper<SOURCE_MODEL, TARGET_MODEL> listItemMapper) {
        this.listItemMapper = nonNull(listItemMapper);
    }

    @Override
    public Optional<List<TARGET_MODEL>> map(List<SOURCE_MODEL> from) {
        if (from == null || from.isEmpty()) {
            return Optional.empty();
        }

        final List<TARGET_MODEL> target = Stream.of(from)
                .filter(toBeMapped -> toBeMapped != null)
                .map(listItemMapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(ImmutableCollectors.toList());

        return target.size() == 0 ? Optional.empty() : Optional.of(target);
    }
}
