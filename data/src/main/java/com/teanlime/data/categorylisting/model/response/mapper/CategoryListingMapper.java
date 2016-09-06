package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.CategoryListingModel;
import com.teanlime.data.categorylisting.model.response.FacetsModel;
import com.teanlime.data.categorylisting.model.response.ListingsModel;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;
import com.teanlime.domain.categorylisting.model.response.Facets;
import com.teanlime.domain.categorylisting.model.response.Listings;

public class CategoryListingMapper implements Mapper<CategoryListingModel, CategoryListing> {

    private final ListMapper<ListingsModel, Listings> listingsMapper;
    private final ListMapper<FacetsModel, Facets> facetsMapper;

    public CategoryListingMapper(ListMapper<ListingsModel, Listings> listingsMapper,
                                 ListMapper<FacetsModel, Facets> facetsMapper) {

        this.listingsMapper = listingsMapper;
        this.facetsMapper = facetsMapper;
    }


    @Override
    public Optional<CategoryListing> map(CategoryListingModel from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new CategoryListing(
                listingsMapper.map(from.getAlsoSearched()).orElse(null),
                listingsMapper.map(from.getListings()).orElse(null),
                facetsMapper.map(from.getFacets()).orElse(null),
                from.getDescription(),
                from.getRedirectUrl(),
                from.getItemCount(),
                from.getSortType()));
    }
}
