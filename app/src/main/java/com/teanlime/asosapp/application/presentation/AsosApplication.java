package com.teanlime.asosapp.application.presentation;

import android.app.Application;
import android.content.Context;
import android.os.Build;

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

        applicationComponent = createApplicationComponent();
        applicationComponent.inject(this);
    }

    protected ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
