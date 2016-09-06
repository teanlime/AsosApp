package com.teanlime.asosapp.categorylisting.model;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.base.model.FragmentComponentFactory;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.categorylisting.di.CategoryListingFragmentComponent;
import com.teanlime.asosapp.categorylisting.di.CategoryListingFragmentModule;
import com.teanlime.asosapp.categorylisting.di.DaggerCategoryListingFragmentComponent;
import com.teanlime.asosapp.home.presentation.HomeActivity;

public class CategoryListingFragmentComponentFactory implements FragmentComponentFactory<CategoryListingFragmentComponent> {

    @Override
    public CategoryListingFragmentComponent create(Fragment fragment) {
        return DaggerCategoryListingFragmentComponent.builder()
                .categoryListingFragmentModule(new CategoryListingFragmentModule(fragment))
                .homeActivityComponent(AsosActivity.<HomeActivity>get(fragment).getActivityComponent())
                .build();
    }
}
