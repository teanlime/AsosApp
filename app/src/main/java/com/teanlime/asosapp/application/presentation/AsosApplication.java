package com.teanlime.asosapp.application.presentation;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;

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

    public static AsosApplication get(Fragment fragment) {
        return (AsosApplication) fragment.getActivity().getApplicationContext();
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

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
