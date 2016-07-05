package com.teanlime.data.api.usecase;

import com.teanlime.data.api.repository.Repository;
import com.teanlime.domain.api.usecase.UseCase;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import rx.Subscription;

public class RepositoryUseCase<M, E> implements UseCase<M, E> {

    private final UseCaseCallbackDecorator<M, E> useCaseCallbackDecorator;
    private final RxSchedulerFactory schedulerFactory;
    private final Repository<M> repository;

    private Subscription subscription;

    protected RepositoryUseCase(UseCaseCallbackDecorator<M, E> useCaseCallbackDecorator,
                                RxSchedulerFactory schedulerFactory,
                                Repository<M> repository) {
        this.useCaseCallbackDecorator = useCaseCallbackDecorator;
        this.schedulerFactory = schedulerFactory;
        this.repository = repository;
    }

    @Override
    public void execute(UseCaseCallback<M, E> callback) {
        subscription = repository.getData()
                .subscribeOn(schedulerFactory.getExecutionScheduler())
                .observeOn(schedulerFactory.getPostExecutionScheduler())
                .subscribe(useCaseCallbackDecorator.callback(callback));
    }

    @Override
    public void cancel() {
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }
}
