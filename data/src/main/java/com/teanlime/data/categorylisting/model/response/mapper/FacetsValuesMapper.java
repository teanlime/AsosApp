package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.FacetValuesModel;
import com.teanlime.domain.categorylisting.model.response.FacetValues;

import javax.inject.Inject;

public class FacetsValuesMapper implements Mapper<FacetValuesModel, FacetValues> {

    @Inject
    public FacetsValuesMapper() {

    }

    @Override
    public Optional<FacetValues> map(FacetValuesModel from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new FacetValues(from.getCount(), from.getName(), from.getId()));
    }
}
