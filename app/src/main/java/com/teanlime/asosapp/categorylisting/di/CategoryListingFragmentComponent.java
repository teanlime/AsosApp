package com.teanlime.asosapp.categorylisting.di;

import com.teanlime.asosapp.categorylisting.presentation.CategoryListingFragment;
import com.teanlime.asosapp.home.di.HomeActivityComponent;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(
        modules = {
                CategoryListingFragmentModule.class
        },
        dependencies = {
                HomeActivityComponent.class
        }
)
public interface CategoryListingFragmentComponent {

    void inject(CategoryListingFragment categoryListingFragment);
}
