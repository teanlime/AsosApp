package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.ListingsModel;
import com.teanlime.domain.categorylisting.model.response.Listings;

import javax.inject.Inject;

public class ListingsMapper implements Mapper<ListingsModel, Listings> {

    @Inject
    public ListingsMapper() {

    }

    @Override
    public Optional<Listings> map(ListingsModel from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new Listings(
                from.getProductImageUrl(),
                from.getHasMoreColours(),
                from.getBasePrice(),
                from.getPreviousPrice(),
                from.getCurrentPrice(),
                from.getProductId(),
                from.getInSet(),
                from.getTitle(),
                from.getBrand(),
                from.getRrp()));
    }
}
