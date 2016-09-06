package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.FacetValuesNetwork;
import com.teanlime.domain.category.model.response.FacetValues;

public class FacetsValuesMapper implements Mapper<FacetValuesNetwork, FacetValues> {

    @Override
    public Optional<FacetValues> map(FacetValuesNetwork from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new FacetValues(from.getCount(), from.getName(), from.getId()));
    }
}
