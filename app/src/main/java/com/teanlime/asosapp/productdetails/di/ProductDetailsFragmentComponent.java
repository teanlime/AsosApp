package com.teanlime.asosapp.productdetails.di;

import com.teanlime.asosapp.home.di.HomeActivityComponent;
import com.teanlime.asosapp.productdetails.presentation.ProductDetailsFragment;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(
        modules = {
                ProductDetailsFragmentModule.class
        },
        dependencies = {
                HomeActivityComponent.class
        }
)
public interface ProductDetailsFragmentComponent {

    void inject(ProductDetailsFragment fragment);
}
