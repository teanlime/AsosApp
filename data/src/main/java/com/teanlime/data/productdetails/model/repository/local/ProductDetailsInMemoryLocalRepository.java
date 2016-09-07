package com.teanlime.data.productdetails.model.repository.local;

import com.annimon.stream.Optional;
import com.teanlime.domain.productdetails.model.response.ProductDetails;

import javax.inject.Inject;

import rx.Observable;

public class ProductDetailsInMemoryLocalRepository implements ProductDetailsLocalRepository {

    private ProductDetails productDetails;

    @Inject
    public ProductDetailsInMemoryLocalRepository() {

    }

    @Override
    public void putProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public void eraseProductDetails() {
        productDetails = null;
    }

    @Override
    public Observable<Optional<ProductDetails>> getProductDetails() {
        return Observable.create(subscriber -> {
            if (hasProductDetails()) {
                subscriber.onNext(Optional.of(productDetails));
                subscriber.onCompleted();
            } else {
                subscriber.onError(new NullPointerException("Data is null!"));
            }
        });
    }

    @Override
    public boolean hasProductDetails() {
        return productDetails != null;
    }
}
