package com.teanlime.asosapp.base.model;

import android.app.Activity;

public interface ActivityComponentFactory<T> {

    T create(Activity activity);
}
