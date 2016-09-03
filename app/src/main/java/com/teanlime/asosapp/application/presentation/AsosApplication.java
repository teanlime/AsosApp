package com.teanlime.asosapp.application.presentation;

import android.app.Application;
import android.content.Context;

import com.teanlime.asosapp.application.di.ApplicationComponent;
import com.teanlime.asosapp.application.di.ApplicationModule;
import com.teanlime.asosapp.application.di.DaggerApplicationComponent;

/**
 * Application base
 */
public class AsosApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static AsosApplication get(Context context) {
        return (AsosApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
