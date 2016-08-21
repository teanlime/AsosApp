package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.EmptyUseCaseCallback;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Subscriber;

public class CallbackSubscriber<M, E> extends Subscriber<Optional<M>> {

    private final Mapper<Throwable, E> useCaseExceptionMapper;

    private UseCaseCallback<M, E> useCaseCallback;

    public CallbackSubscriber(Mapper<Throwable, E> useCaseExceptionMapper) {
        this.useCaseExceptionMapper = useCaseExceptionMapper;
        this.useCaseCallback = new EmptyUseCaseCallback<>();
    }

    public void removeCallback() {
        this.useCaseCallback = new EmptyUseCaseCallback<>();
    }

    public CallbackSubscriber<M, E> callback(UseCaseCallback<M, E> useCaseCallback) {
        this.useCaseCallback = useCaseCallback == null ? new EmptyUseCaseCallback<>() : useCaseCallback;
        return this;
    }

    @Override
    public void onCompleted() {
        useCaseCallback.onCompleted();
    }

    @Override
    public void onNext(Optional<M> model) {
        if (model.isPresent()) {
            useCaseCallback.onNext(model.get());
        } else {
            onError(new NullPointerException("Model is null"));
        }
    }

    @Override
    public void onError(Throwable error) {
        useCaseExceptionMapper.map(error).ifPresent(message -> useCaseCallback.onError(message));
    }
}
