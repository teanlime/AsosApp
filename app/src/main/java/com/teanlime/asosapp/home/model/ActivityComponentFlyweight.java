package com.teanlime.asosapp.home.model;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.teanlime.asosapp.base.model.ActivityComponentFactory;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class ActivityComponentFlyweight<T> {

    private final ActivityComponentFactory<T> activityComponentFactory;

    public ActivityComponentFlyweight(@NonNull ActivityComponentFactory<T> activityComponentFactory) {
        this.activityComponentFactory = nonNull(activityComponentFactory);
    }

    @SuppressWarnings("unchecked")
    public T create(@NonNull FragmentActivity activity) {
        final T savedHomeComponent = (T) activity.getLastCustomNonConfigurationInstance();

        if (savedHomeComponent == null) {
            return activityComponentFactory.create(activity);
        }

        return savedHomeComponent;
    }
}
