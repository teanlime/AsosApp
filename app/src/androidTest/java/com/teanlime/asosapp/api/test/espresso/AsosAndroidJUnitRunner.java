package com.teanlime.asosapp.api.test.espresso;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.teanlime.asosapp.application.presentation.TestAsosApplication;

public class AsosAndroidJUnitRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {

        return super.newApplication(cl, TestAsosApplication.class.getName(), context);
    }
}
