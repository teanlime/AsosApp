package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;

/**
 * Class for object conversions
 *
 * @param <SOURCE_MODEL> type of the original object
 * @param <TARGET_MODEL> type of the target object
 */
public interface Mapper<SOURCE_MODEL, TARGET_MODEL> {

    /**
     * Converts one object into another. Due to the fact that object might be missing Optional is returned to wrap
     * away the possible null;
     *
     * @param from object to be converted
     * @return the target object that is the result of the conversion
     */
    Optional<TARGET_MODEL> map(SOURCE_MODEL from);
}