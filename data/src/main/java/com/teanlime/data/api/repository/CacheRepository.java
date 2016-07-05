package com.teanlime.data.api.repository;

import com.teanlime.data.api.local.LocalRepository;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.remote.RemoteRepository;

import rx.Observable;

public class CacheRepository<D, M> implements Repository<M> {

    private final RemoteRepository<D> remoteRepository;
    private final LocalRepository<M> localRepository;
    private final Mapper<D, M> mapper;

    public CacheRepository(RemoteRepository<D> remoteRepository,
                           LocalRepository<M> localRepository,
                           Mapper<D, M> mapper) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.mapper = mapper;
    }

    @Override
    public Observable<M> getData() {
        if (localRepository.hasData()) {
            return localRepository.getData();
        } else {
            return remoteRepository.getData()
                    .map(mapper::transform)
                    .doOnNext(localRepository::putData);
        }
    }
}