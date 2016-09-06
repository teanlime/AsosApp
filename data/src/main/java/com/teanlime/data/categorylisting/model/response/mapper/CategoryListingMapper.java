package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.CategoryListingNetwork;
import com.teanlime.data.categorylisting.model.response.FacetsNetwork;
import com.teanlime.data.categorylisting.model.response.ListingsNetwork;
import com.teanlime.domain.category.model.response.CategoryListing;
import com.teanlime.domain.category.model.response.Facets;
import com.teanlime.domain.category.model.response.Listings;

public class CategoryListingMapper implements Mapper<CategoryListingNetwork, CategoryListing> {

    private final ListMapper<ListingsNetwork, Listings> listingsMapper;
    private final ListMapper<FacetsNetwork, Facets> facetsMapper;

    public CategoryListingMapper(ListMapper<ListingsNetwork, Listings> listingsMapper,
                                 ListMapper<FacetsNetwork, Facets> facetsMapper) {

        this.listingsMapper = listingsMapper;
        this.facetsMapper = facetsMapper;
    }


    @Override
    public Optional<CategoryListing> map(CategoryListingNetwork from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new CategoryListing(
                listingsMapper.map(from.getAlsoSearched()).get(),
                listingsMapper.map(from.getListings()).get(),
                facetsMapper.map(from.getFacets()).get(),
                from.getDescription(),
                from.getRedirectUrl(),
                from.getItemCount(),
                from.getSortType()));
    }
}
