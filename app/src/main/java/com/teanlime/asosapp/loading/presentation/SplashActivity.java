package com.teanlime.asosapp.loading.presentation;

import android.os.Bundle;

import com.teanlime.asosapp.base.presentation.AsosActivity;
import com.teanlime.asosapp.home.presentation.HomeActivity;

public class SplashActivity extends AsosActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(HomeActivity.createStartIntent(this));
    }
}
