package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Use case that resolves the result of the execution by the use of RxJava
 *
 * @param <M> successful response type
 * @param <E> error response type
 */
public class RxUseCaseSubscription<M, E> {

    private final CallbackSubscriber<M, E> callbackSubscriber;
    private final RxSchedulerFactory schedulerFactory;

    private Subscription subscription;

    public RxUseCaseSubscription(CallbackSubscriber<M, E> callbackSubscriber,
                                 RxSchedulerFactory schedulerFactory) {

        this.callbackSubscriber = nonNull(callbackSubscriber);
        this.schedulerFactory = nonNull(schedulerFactory);
    }

    public void subscribe(Observable<Optional<M>> observable, UseCaseCallback<M, E> callback) {
        subscription = observable.subscribeOn(schedulerFactory.getExecutionScheduler())
                .observeOn(schedulerFactory.getPostExecutionScheduler())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        callbackSubscriber.removeCallback();
                    }
                })
                //.doOnCompleted(() -> callbackSubscriber.callback(null))
                .subscribe(callbackSubscriber.callback(callback));
    }

    public void cancel() {
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }
}
