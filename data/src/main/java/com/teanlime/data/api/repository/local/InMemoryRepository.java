package com.teanlime.data.api.repository.local;

import rx.Observable;

public class InMemoryRepository<M> implements LocalRepository<M> {

    private M data;

    @Override
    public void putData(M data) {
        this.data = data;
    }

    @Override
    public Observable<M> getData() {
        return Observable.create(subscriber -> {
            if (hasData()) {
                subscriber.onNext(data);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new NullPointerException("Data is null!"));
            }
        });
    }

    @Override
    public boolean hasData() {
        return data != null;
    }

    @Override
    public void eraseData() {
        data = null;
    }
}
