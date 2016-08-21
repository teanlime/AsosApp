package com.teanlime.asosapp.base.presentation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.teanlime.asosapp.R;

/**
 * Base activity for shared components
 */
public abstract class AsosActivity extends AppCompatActivity {

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        //ButterKnife.bind(this);
    }

    @LayoutRes
    protected int getLayoutResource() {
        return R.layout.activity_content;
    }
}
