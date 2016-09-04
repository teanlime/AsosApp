package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Subscriber;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Implementation of RxJava Subscriber. Encapsulates the RxJava API events and delegates them to a UseCaseCallback
 * instead.
 * Handles successes and errors of specified types, which are Optional.
 * Handles empty response as error
 * Once onComplete is called, it removes reference to the previous callback
 *
 * @param <M> Model type
 * @param <E> Exception type
 */
class CallbackDelegateSubscriber<M, E> extends Subscriber<Optional<M>> {

    private final Mapper<Throwable, E> useCaseExceptionMapper;
    private UseCaseCallback<M, E> useCaseCallback;

    CallbackDelegateSubscriber(Mapper<Throwable, E> useCaseExceptionMapper,
                               UseCaseCallback<M, E> useCaseCallback) {

        this.useCaseExceptionMapper = nonNull(useCaseExceptionMapper);
        this.useCaseCallback = nonNull(useCaseCallback);
    }

    /**
     * RxJava Subscriber implementation. Delegates to UseCaseCallback
     */
    @Override
    public void onCompleted() {
        useCaseCallback.onCompleted();
        useCaseCallback = null;
    }

    /**
     * RxJava Subscriber implementation. If data is present, delegates onNext to UseCaseCallback
     * Otherwise processes error
     *
     * @param model received object
     */
    @Override
    public void onNext(Optional<M> model) {
        if (model.isPresent()) {
            useCaseCallback.onNext(model.get());
        } else {
            onError(new NullPointerException("Model is null"));
        }
    }

    /**
     * RxJava Subscriber implementation. Maps the Throwable into a given error type E and delegates to the
     * UseCaseCallback
     *
     * @param error received error to be mapped into E
     */
    @Override
    public void onError(Throwable error) {
        useCaseExceptionMapper.map(error).ifPresent(useCaseCallback::onError);
    }
}
