package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;

public class StringUseCaseExceptionMapper implements Mapper<Throwable, String> {

    @Override
    public Optional<String> map(Throwable from) {
        return Optional.of(from.getLocalizedMessage());
    }
}
