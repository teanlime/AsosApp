package com.teanlime.data.productdetails.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.domain.productdetails.model.response.ProductDetails;

import rx.Observable;

public interface ProductDetailsRepository {

    Observable<Optional<ProductDetails>> getProductDetails();
}
