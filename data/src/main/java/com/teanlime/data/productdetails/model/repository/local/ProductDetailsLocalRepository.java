package com.teanlime.data.productdetails.model.repository.local;

import com.teanlime.data.productdetails.model.repository.ProductDetailsRepository;
import com.teanlime.domain.productdetails.model.response.ProductDetails;

public interface ProductDetailsLocalRepository extends ProductDetailsRepository {

    void putProductDetails(ProductDetails productDetails);

    boolean hasProductDetails();

    void eraseProductDetails();
}
