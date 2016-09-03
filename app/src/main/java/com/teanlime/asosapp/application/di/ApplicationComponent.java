package com.teanlime.asosapp.application.di;

import android.app.Application;
import android.content.Context;

import com.teanlime.asosapp.application.presentation.AsosApplication;
import com.teanlime.asosapp.utils.di.dagger.qualifier.context.ApplicationContext;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = ApplicationModule.class
)
public interface ApplicationComponent {

    void inject(AsosApplication asosApplication);

    @ApplicationContext
    Context context();

    Application application();

    RetrofitRemoteRestService retrofitRemoteRestService();
}
