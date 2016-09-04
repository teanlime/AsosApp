package com.teanlime.domain.api.presentation;

public abstract class Presenter<V> {

    private final V emptyView;
    protected V view;

    protected Presenter(V emptyView) {
        this.emptyView = emptyView;
        this.view = emptyView;
    }

    public void detachView() {
        this.view = this.emptyView;
    }

    public void attachView(V view) {
        this.view = view;
    }
}
