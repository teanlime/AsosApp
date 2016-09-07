package com.teanlime.data.productdetails.model.response.mapper;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.productdetails.model.response.ProductDetailsModel;
import com.teanlime.domain.api.util.streams.ImmutableCollectors;
import com.teanlime.domain.productdetails.model.response.ProductDetails;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ProductDetailsMapper implements Mapper<ProductDetailsModel, ProductDetails> {

    @Inject
    public ProductDetailsMapper() {

    }

    @Override
    public Optional<ProductDetails> map(ProductDetailsModel from) {
        if (from == null) {
            return Optional.empty();
        }

        return Optional.of(new ProductDetails(
                        mapList(from.getAssociatedProducts()),
                        mapList(from.getVariants()),
                        Collections.unmodifiableList(from.getProductImageUrls()),
                        from.getAdditionalInfo(),
                        from.getPreviousPrice(),
                        from.getCurrentPrice(),
                        from.getDescription(),
                        from.getBasePrice(),
                        from.getPriceType(),
                        from.getProductId(),
                        from.getCareInfo(),
                        from.getInStock(),
                        from.getInSet(),
                        from.getColour(),
                        from.getBrand(),
                        from.getTitle(),
                        from.getSize(),
                        from.getSku(),
                        from.getRrp()
                )
        );
    }

    private List<ProductDetails> mapList(List<ProductDetailsModel> from) {
        if (from == null) {
            return null;
        }
        return Stream.of(from)
                .filter(toBeMapped -> toBeMapped != null)
                .map(this::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(ImmutableCollectors.toList());
    }
}
