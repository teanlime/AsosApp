package com.teanlime.asosapp.home.di;

import com.teanlime.asosapp.application.di.ApplicationComponent;
import com.teanlime.asosapp.home.presentation.HomeActivity;
import com.teanlime.asosapp.utils.di.dagger.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = HomeActivityModule.class
)
public interface HomeActivityComponent {

    void inject(HomeActivity homeActivity);
}
