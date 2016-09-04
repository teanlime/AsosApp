package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Subscriber;

public interface SubscriberFactory<M, E> {

    Subscriber<Optional<M>> create(UseCaseCallback<M, E> useCaseCallback);
}
