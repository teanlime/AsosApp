package com.teanlime.data.api.repository;

import rx.Observable;

public interface Repository<M> {

    Observable<M> getData();
}
