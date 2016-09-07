package com.teanlime.data.productdetails.model.repository.remote;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.productdetails.model.repository.ProductDetailsRepository;
import com.teanlime.data.productdetails.model.response.ProductDetailsModel;
import com.teanlime.domain.productdetails.model.response.ProductDetails;

import javax.inject.Inject;

import rx.Observable;

public class ProductDetailsRemoteRepository implements ProductDetailsRepository {

    private final Mapper<ProductDetailsModel, ProductDetails> productDetailsMapper;
    private final RemoteService service;
    private final String productId;

    @Inject
    public ProductDetailsRemoteRepository(Mapper<ProductDetailsModel, ProductDetails> productDetailsMapper,
                                          RemoteService service,
                                          String productId) {

        this.productDetailsMapper = productDetailsMapper;
        this.productId = productId;
        this.service = service;
    }


    @Override
    public Observable<Optional<ProductDetails>> getProductDetails() {
        return service.getProductDetails(productId).map(productDetailsMapper::map);
    }
}

