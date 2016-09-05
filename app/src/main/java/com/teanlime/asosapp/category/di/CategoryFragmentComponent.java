package com.teanlime.asosapp.category.di;

import com.teanlime.asosapp.category.presentation.CategoryFragment;
import com.teanlime.asosapp.home.di.HomeActivityComponent;
import com.teanlime.asosapp.utils.di.dagger.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(
        modules = {
                CategoryFragmentModule.class
        },
        dependencies = {
                HomeActivityComponent.class
        }
)
public interface CategoryFragmentComponent {

    void inject(CategoryFragment categoryFragment);
}
