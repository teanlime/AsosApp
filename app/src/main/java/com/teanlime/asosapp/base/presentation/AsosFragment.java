package com.teanlime.asosapp.base.presentation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base activity for shared components
 */
public abstract class AsosFragment<T> extends Fragment {

    private T fragmentComponent;

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutResource(), container);
        ButterKnife.bind(view);
        fragmentComponent = createFragmentComponent();
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    @NonNull
    protected abstract T createFragmentComponent();

    public T getFragmentComponent() {
        return fragmentComponent;
    }
}
