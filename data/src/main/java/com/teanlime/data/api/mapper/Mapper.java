package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;

public interface Mapper<SOURCE_MODEL, TARGET_MODEL> {

    Optional<TARGET_MODEL> transform(SOURCE_MODEL from);
}