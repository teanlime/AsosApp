package com.teanlime.domain.api.usecase;

public interface UseCase<M, E> {

    void execute(UseCaseCallback<M, E> callback);

    void cancel();
}