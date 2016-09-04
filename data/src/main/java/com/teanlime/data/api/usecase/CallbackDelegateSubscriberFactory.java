package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import javax.inject.Inject;

import rx.Subscriber;

import static com.teanlime.domain.api.util.Validate.nonNull;

public class CallbackDelegateSubscriberFactory<M, E> implements SubscriberFactory<M, E> {

    private final Mapper<Throwable, E> useCaseExceptionMapper;

    @Inject
    public CallbackDelegateSubscriberFactory(Mapper<Throwable, E> useCaseExceptionMapper) {
        this.useCaseExceptionMapper = nonNull(useCaseExceptionMapper);
    }

    public Subscriber<Optional<M>> create(UseCaseCallback<M, E> useCaseCallback) {
        return new CallbackDelegateSubscriber<>(useCaseExceptionMapper, useCaseCallback);
    }
}
