package com.teanlime.domain.api.presentation;

import static com.teanlime.domain.api.util.Validate.nonNull;

public abstract class Presenter<V> {

    private final V emptyView;
    protected V view;

    protected Presenter(V emptyView) {
        this.emptyView = nonNull(emptyView);
        this.view = emptyView;
    }

    public void detachView() {
        this.view = this.emptyView;
    }

    public void attachView(V view) {
        this.view = view;
    }
}
