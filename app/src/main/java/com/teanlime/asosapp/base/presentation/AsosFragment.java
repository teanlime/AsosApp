package com.teanlime.asosapp.base.presentation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base fragment for shared components
 */
public abstract class AsosFragment extends Fragment {

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(getLayoutResource(), container);
        ButterKnife.bind(fragmentView);
        return fragmentView;
    }

    @LayoutRes
    protected abstract int getLayoutResource();
}
