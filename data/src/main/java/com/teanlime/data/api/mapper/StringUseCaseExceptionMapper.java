package com.teanlime.data.api.mapper;

import com.annimon.stream.Optional;
import com.teanlime.domain.api.util.StringUtils;

import javax.inject.Inject;

/**
 * Maps Throwable into a localised message
 */
public class StringUseCaseExceptionMapper implements Mapper<Throwable, String> {

    @Inject
    public StringUseCaseExceptionMapper() {

    }

    @Override
    public Optional<String> map(Throwable from) {
        return isValid(from) ? Optional.of(from.getLocalizedMessage()) : Optional.empty();
    }

    private boolean isValid(Throwable from) {
        return from != null && StringUtils.isNotBlank(from.getLocalizedMessage());
    }
}
