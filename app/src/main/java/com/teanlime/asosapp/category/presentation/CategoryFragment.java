package com.teanlime.asosapp.category.presentation;

import android.support.annotation.NonNull;

import com.teanlime.asosapp.base.model.FragmentComponentFlyweight;
import com.teanlime.asosapp.base.presentation.AsosFragment;
import com.teanlime.asosapp.category.di.CategoryFragmentComponent;
import com.teanlime.asosapp.category.model.CategoryFragmentComponentFactory;

public class CategoryFragment extends AsosFragment<CategoryFragmentComponent> {

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @NonNull
    @Override
    protected CategoryFragmentComponent createFragmentComponent() {
        final CategoryFragmentComponent categoryFragmentComponent = new FragmentComponentFlyweight<>(new
                CategoryFragmentComponentFactory()).create(this);
        categoryFragmentComponent.inject(this);
        return categoryFragmentComponent;
    }
}
