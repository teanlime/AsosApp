package com.teanlime.asosapp.categorylisting.di;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.categorylisting.presentation.CategoryListingFragment;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;

import dagger.Module;

@Module
public class CategoryListingFragmentModule {

    private final CategoryListingFragment fragment;

    public CategoryListingFragmentModule(Fragment fragment) {
        this.fragment = (CategoryListingFragment) fragment;
    }

    @PerFragment
    public CategoryListingFragment categoryListingFragment() {
        return fragment;
    }
}
