package com.teanlime.domain.api.usecase;

public interface UseCaseCallback<M, E> {

    void onCompleted();

    void onError(E exception);

    void onNext(M model);
}
