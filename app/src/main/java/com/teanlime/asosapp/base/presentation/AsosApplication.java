package com.teanlime.asosapp.base.presentation;

import android.app.Application;

/**
 * Application base
 */
public class AsosApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO Create dev preference for butterknife debug
        //ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
