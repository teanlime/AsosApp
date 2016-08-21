package com.teanlime.data.api.usecase;

import com.teanlime.domain.api.usecase.UseCase;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;

public abstract class RepositoryUseCase<M, E> implements UseCase<M, E> {

    private final CallbackSubscriber<M, E> callbackSubscriber;
    private final RxSchedulerFactory schedulerFactory;

    private Subscription subscription;

    protected RepositoryUseCase(CallbackSubscriber<M, E> callbackSubscriber,
                                RxSchedulerFactory schedulerFactory) {
        this.callbackSubscriber = callbackSubscriber;
        this.schedulerFactory = schedulerFactory;
    }

    @Override
    public void execute(UseCaseCallback<M, E> callback) {
        subscription = getData()
                .subscribeOn(schedulerFactory.getExecutionScheduler())
                .observeOn(schedulerFactory.getPostExecutionScheduler())
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        callbackSubscriber.callback(null);
                    }
                })
                //.doOnCompleted(() -> callbackSubscriber.callback(null))
                .subscribe(callbackSubscriber.callback(callback));
    }

    protected abstract Observable<M> getData();

    @Override
    public void cancel() {
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }
}
