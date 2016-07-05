package com.teanlime.data.api.local;

import com.teanlime.data.api.repository.Repository;

public interface LocalRepository<M> extends Repository<M> {

    void putData(M data);

    boolean hasData();

    void eraseData();
}
