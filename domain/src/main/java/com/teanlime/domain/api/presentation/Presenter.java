package com.teanlime.domain.api.presentation;

public abstract class Presenter<V> {

    private V view;

    public Presenter() {
        detachView();
    }

    public void detachView() {
        this.view = getEmptyView();
    }

    protected abstract V getEmptyView();

    public void attachView(V view) {
        this.view = view;
    }
}
