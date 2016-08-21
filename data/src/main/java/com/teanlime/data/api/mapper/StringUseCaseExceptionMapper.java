package com.teanlime.data.api.mapper;

public class StringUseCaseExceptionMapper implements Mapper<Throwable, String> {

    @Override
    public String transform(Throwable from) {
        return from.getLocalizedMessage();
    }
}
