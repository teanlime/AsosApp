package com.teanlime.data.api.usecase;

import com.teanlime.domain.api.usecase.UseCase;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Observable;
import rx.Subscription;

public abstract class RxUseCase<M, E> implements UseCase<M, E> {

    private final RxSchedulerFactory schedulerFactory;

    private Subscription subscription;

    protected RxUseCase(RxSchedulerFactory schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
    }

    @Override
    public void execute(UseCaseCallback<M, E> callback) {
        subscription = buildObservable()
                .subscribeOn(schedulerFactory.getExecutionScheduler())
                .observeOn(schedulerFactory.getPostExecutionScheduler())
                .subscribe(buildUseCaseCallbackDecorator(callback));
    }

    abstract Observable<M> buildObservable();

    abstract UseCaseCallbackDecorator<M, E> buildUseCaseCallbackDecorator(UseCaseCallback callback);

    @Override
    public void cancel() {
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }
}
