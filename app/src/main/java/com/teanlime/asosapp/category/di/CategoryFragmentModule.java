package com.teanlime.asosapp.category.di;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.category.presentation.CategoryFragment;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;

import dagger.Module;

@Module
public class CategoryFragmentModule {

    private final CategoryFragment fragment;

    public CategoryFragmentModule(Fragment fragment) {
        this.fragment = (CategoryFragment) fragment;
    }

    @PerFragment
    public CategoryFragment categoryFragment() {
        return fragment;
    }
}
