package com.teanlime.asosapp.productdetails.model;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.base.model.FragmentComponentFactory;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.home.presentation.HomeActivity;
import com.teanlime.asosapp.productdetails.di.DaggerProductDetailsFragmentComponent;
import com.teanlime.asosapp.productdetails.di.ProductDetailsFragmentComponent;
import com.teanlime.asosapp.productdetails.di.ProductDetailsFragmentModule;

public class ProductDetailsFragmentComponentFactory implements
        FragmentComponentFactory<ProductDetailsFragmentComponent> {

    private final String productId;

    public ProductDetailsFragmentComponentFactory(String productId) {
        this.productId = productId;
    }

    @Override
    public ProductDetailsFragmentComponent create(Fragment fragment) {
        return DaggerProductDetailsFragmentComponent.builder()
                .productDetailsFragmentModule(new ProductDetailsFragmentModule(fragment, productId))
                .homeActivityComponent(AsosActivity.<HomeActivity>get(fragment).getActivityComponent())
                .build();
    }
}
