package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.FacetValuesModel;
import com.teanlime.data.categorylisting.model.response.FacetsModel;
import com.teanlime.domain.categorylisting.model.response.FacetValues;
import com.teanlime.domain.categorylisting.model.response.Facets;

import javax.inject.Inject;

public class FacetsMapper implements Mapper<FacetsModel, Facets> {

    private final ListMapper<FacetValuesModel, FacetValues> facetsValuesMapper;

    @Inject
    public FacetsMapper(ListMapper<FacetValuesModel, FacetValues> facetsValuesMapper) {
        this.facetsValuesMapper = facetsValuesMapper;
    }

    @Override
    public Optional<Facets> map(FacetsModel from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new Facets(facetsValuesMapper.map(from.getFacetValues()).orElse(null),
                from.getSequence(),
                from.getName(),
                from.getId()));
    }
}
