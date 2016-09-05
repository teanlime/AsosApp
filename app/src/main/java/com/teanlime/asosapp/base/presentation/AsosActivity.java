package com.teanlime.asosapp.base.presentation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.teanlime.asosapp.R;

import butterknife.ButterKnife;

/**
 * Base activity for shared components
 */
public abstract class AsosActivity<T> extends AppCompatActivity {

    private T activityComponent;

    public static <T extends AsosActivity> T get(Fragment fragment) {
        return (T) fragment.getActivity();
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        ButterKnife.bind(this);

        activityComponent = createActivityComponent();
    }

    @LayoutRes
    protected int getLayoutResource() {
        return R.layout.content;
    }

    @NonNull
    protected abstract T createActivityComponent();

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return getActivityComponent();
    }

    public T getActivityComponent() {
        return activityComponent;
    }
}
