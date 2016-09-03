package com.teanlime.domain.api.presentation;

public abstract class Presenter<V> {

    private V emptyView;
    private V view;

    public Presenter(V emptyView) {
        this.emptyView = emptyView;

        detachView();
    }

    public void detachView() {
        this.view = this.emptyView;
    }

    public void attachView(V view) {
        this.view = view;
    }
}
