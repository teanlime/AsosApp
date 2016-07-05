package com.teanlime.data.categories.model.repository.remote;

import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import rx.Observable;

public class CategoriesRemoteRepository implements CategoriesRepository {

    private final Mapper<CategoriesGroup, String> categoriesGroupQueryMapper;
    private final Mapper<CategoriesModel, Categories> responseMapper;
    private final RemoteService service;

    public CategoriesRemoteRepository(Mapper<CategoriesGroup, String> categoriesGroupQueryMapper,
                                      Mapper<CategoriesModel, Categories> responseMapper,
                                      RemoteService service) {
        this.categoriesGroupQueryMapper = categoriesGroupQueryMapper;
        this.responseMapper = responseMapper;
        this.service = service;
    }

    @Override
    public Observable<Categories> getCategoriesForGroup(CategoriesGroup categoriesGroup) {
        return service.getCategories(categoriesGroupQueryMapper.transform(categoriesGroup))
                .map(responseMapper::transform);
    }
}

