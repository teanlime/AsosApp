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
import butterknife.Unbinder;

/**
 * Base activity for shared components
 */
public abstract class AsosFragment<T> extends Fragment {

    private T fragmentComponent;
    private Unbinder unbinder;

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutResource(), container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentComponent = createFragmentComponent();
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    @NonNull
    protected abstract T createFragmentComponent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public T getFragmentComponent() {
        return fragmentComponent;
    }
}
