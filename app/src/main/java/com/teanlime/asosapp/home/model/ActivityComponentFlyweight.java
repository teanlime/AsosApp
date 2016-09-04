package com.teanlime.asosapp.home.model;

import android.support.annotation.NonNull;

import com.teanlime.asosapp.base.model.ActivityComponentFactory;
import com.teanlime.asosapp.base.presentation.AsosActivity;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class ActivityComponentFlyweight<T> {

    private final ActivityComponentFactory<T> activityComponentFactory;

    public ActivityComponentFlyweight(@NonNull ActivityComponentFactory<T> activityComponentFactory) {
        this.activityComponentFactory = nonNull(activityComponentFactory);
    }

    @SuppressWarnings("unchecked")
    public T create(@NonNull AsosActivity<T> activity) {
        final T currentActivityComponent = activity.getActivityComponent();
        if (currentActivityComponent != null) {
            return currentActivityComponent;
        }

        final T savedHomeComponent = (T) activity.getLastCustomNonConfigurationInstance();
        if (savedHomeComponent != null) {
            return savedHomeComponent;
        }

        return activityComponentFactory.create(activity);
    }
}
