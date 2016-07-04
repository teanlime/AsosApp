package com.teanlime.data.api.mapper;

public interface Mapper<SOURCE_MODEL, TARGET_MODEL> {

    TARGET_MODEL transform(SOURCE_MODEL from);
}
