package com.teanlime.domain.api.usecase;

public class EmptyUseCaseCallback<M, D> implements UseCaseCallback<M, D> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(D exception) {

    }

    @Override
    public void onNext(M model) {

    }
}
