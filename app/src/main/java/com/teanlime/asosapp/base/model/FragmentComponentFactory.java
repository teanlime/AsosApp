package com.teanlime.asosapp.base.model;

import android.support.v4.app.Fragment;

public interface FragmentComponentFactory<T> {

    T create(Fragment fragment);
}
