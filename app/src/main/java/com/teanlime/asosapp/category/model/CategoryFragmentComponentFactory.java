package com.teanlime.asosapp.category.model;

import android.support.v4.app.Fragment;

import com.teanlime.asosapp.base.model.FragmentComponentFactory;
import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.category.di.CategoryFragmentComponent;
import com.teanlime.asosapp.category.di.CategoryFragmentModule;
import com.teanlime.asosapp.category.di.DaggerCategoryFragmentComponent;
import com.teanlime.asosapp.home.presentation.HomeActivity;

public class CategoryFragmentComponentFactory implements FragmentComponentFactory<CategoryFragmentComponent> {

    @Override
    public CategoryFragmentComponent create(Fragment fragment) {
        return DaggerCategoryFragmentComponent.builder()
                .categoryFragmentModule(new CategoryFragmentModule(fragment))
                .homeActivityComponent(AsosActivity.<HomeActivity>get(fragment).getActivityComponent())
                .build();
    }
}
