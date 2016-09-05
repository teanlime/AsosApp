package com.teanlime.asosapp.base.model;

import android.support.annotation.NonNull;

import com.teanlime.asosapp.base.presentation.AsosFragment;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class FragmentComponentFlyweight<T> {

    private final FragmentComponentFactory<T> fragmentComponentFactory;

    public FragmentComponentFlyweight(@NonNull FragmentComponentFactory<T> fragmentComponentFactory) {
        this.fragmentComponentFactory = nonNull(fragmentComponentFactory);
    }

    @SuppressWarnings("unchecked")
    public T create(@NonNull AsosFragment<T> fragment) {
        final T currentFragmentComponent = fragment.getFragmentComponent();
        if (currentFragmentComponent != null) {
            return currentFragmentComponent;
        }

        return fragmentComponentFactory.create(fragment);
    }
}
