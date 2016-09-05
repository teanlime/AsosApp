package com.teanlime.asosapp.home.model;

import android.app.Activity;

import com.teanlime.asosapp.application.presentation.AsosApplication;
import com.teanlime.asosapp.base.model.ActivityComponentFactory;
import com.teanlime.asosapp.home.di.DaggerHomeActivityComponent;
import com.teanlime.asosapp.home.di.HomeActivityComponent;
import com.teanlime.asosapp.home.di.HomeActivityModule;

public class HomeActivityComponentFactory implements ActivityComponentFactory<HomeActivityComponent> {

    @Override
    public HomeActivityComponent create(Activity activity) {
        return DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(activity))
                .applicationComponent(AsosApplication.get(activity).getApplicationComponent())
                .build();
    }
}
