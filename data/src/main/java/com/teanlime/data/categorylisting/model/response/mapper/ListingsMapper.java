package com.teanlime.data.categorylisting.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categorylisting.model.response.ListingsNetwork;
import com.teanlime.domain.category.model.response.Listings;

public class ListingsMapper implements Mapper<ListingsNetwork, Listings> {

    @Override
    public Optional<Listings> map(ListingsNetwork from) {
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
