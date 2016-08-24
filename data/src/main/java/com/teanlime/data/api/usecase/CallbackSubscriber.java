package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.EmptyUseCaseCallback;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Subscriber;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Implementation of RxJava Subscriber. Encapsulates the RxJava API events and delegates them to a UseCaseCallback
 * instead.
 * Handles successes and errors of specified types, which are Optional.
 * Handles empty response as error
 *
 * @param <M> Model type
 * @param <E> Exception type
 */
public class CallbackSubscriber<M, E> extends Subscriber<Optional<M>> {

    private final EmptyUseCaseCallback<M, E> emptyUseCaseCallback;
    private final Mapper<Throwable, E> useCaseExceptionMapper;

    private UseCaseCallback<M, E> useCaseCallback;

    public CallbackSubscriber(EmptyUseCaseCallback<M, E> emptyUseCaseCallback,
                              Mapper<Throwable, E> useCaseExceptionMapper) {

        this.useCaseExceptionMapper = nonNull(useCaseExceptionMapper);
        this.emptyUseCaseCallback = nonNull(emptyUseCaseCallback);

        this.useCaseCallback = emptyUseCaseCallback;
    }

    /**
     * Sets a new callback of the Subscriber events
     *
     * @param useCaseCallback new callback
     * @return this
     */
    public CallbackSubscriber<M, E> callback(UseCaseCallback<M, E> useCaseCallback) {
        this.useCaseCallback = useCaseCallback == null ? emptyUseCaseCallback : useCaseCallback;
        return this;
    }

    /**
     * RxJava Subscriber implementation. Delegates to UseCaseCallback
     */
    @Override
    public void onCompleted() {
        useCaseCallback.onCompleted();
        useCaseCallback = emptyUseCaseCallback;
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
        useCaseExceptionMapper.map(error).ifPresent(message -> useCaseCallback.onError(message));
    }
}
