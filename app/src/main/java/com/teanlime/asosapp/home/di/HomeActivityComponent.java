package com.teanlime.asosapp.home.di;

import com.teanlime.asosapp.application.di.ApplicationComponent;
import com.teanlime.asosapp.home.presentation.HomeActivity;
import com.teanlime.asosapp.utils.di.dagger.scope.PerActivity;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.api.usecase.RxSchedulerFactory;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = HomeActivityModule.class
)
public interface HomeActivityComponent {

    void inject(HomeActivity homeActivity);

    RxSchedulerFactory rxSchedulerFactory();

    RetrofitRemoteRestService retrofitRemoteRestService();
}
