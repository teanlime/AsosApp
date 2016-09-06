package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.FacetValuesNetwork;
import com.teanlime.data.categorylisting.model.response.FacetsNetwork;
import com.teanlime.domain.category.model.response.FacetValues;
import com.teanlime.domain.category.model.response.Facets;

public class FacetsMapper implements Mapper<FacetsNetwork, Facets> {

    private final ListMapper<FacetValuesNetwork, FacetValues> facetsValuesMapper;

    public FacetsMapper(ListMapper<FacetValuesNetwork, FacetValues> facetsValuesMapper) {
        this.facetsValuesMapper = facetsValuesMapper;
    }

    @Override
    public Optional<Facets> map(FacetsNetwork from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new Facets(facetsValuesMapper.map(from.getFacetValues()).get(),
                from.getSequence(),
                from.getName(),
                from.getId()));
    }
}
