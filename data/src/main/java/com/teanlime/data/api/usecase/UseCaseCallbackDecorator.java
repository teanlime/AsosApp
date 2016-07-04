package com.teanlime.data.api.usecase;

import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Subscriber;

public class UseCaseCallbackDecorator<M, E> extends Subscriber<M> {

    private final Mapper<Throwable, E> useCaseExceptionMapper;
    private final UseCaseCallback<M, E> useCaseCallback;

    public UseCaseCallbackDecorator(Mapper<Throwable, E> useCaseExceptionMapper,
                                    UseCaseCallback<M, E> useCaseCallback) {
        this.useCaseExceptionMapper = useCaseExceptionMapper;
        this.useCaseCallback = useCaseCallback;
    }

    @Override
    public void onCompleted() {
        useCaseCallback.onCompleted();
    }

    @Override
    public void onNext(M model) {
        if (model == null) {
            onError(new NullPointerException("Model is null"));
        }
        useCaseCallback.onNext(model);
    }

    @Override
    public void onError(Throwable e) {
        useCaseCallback.onError(useCaseExceptionMapper.transform(e));
    }
}
