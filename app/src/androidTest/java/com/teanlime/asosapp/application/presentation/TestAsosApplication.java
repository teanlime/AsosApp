package com.teanlime.asosapp.application.presentation;

import com.teanlime.asosapp.application.di.ApplicationComponent;
import com.teanlime.asosapp.application.di.DaggerApplicationComponent;
import com.teanlime.asosapp.application.di.TestApplicationModule;

public class TestAsosApplication extends AsosApplication {

    @Override
    protected ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new TestApplicationModule(this))
                .build();
    }
}