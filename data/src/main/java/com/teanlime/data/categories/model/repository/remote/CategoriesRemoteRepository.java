package com.teanlime.data.categories.model.repository.remote;

import com.annimon.stream.Optional;
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
    private final String fallbackCategoryGroup;
    private final RemoteService service;

    public CategoriesRemoteRepository(Mapper<CategoriesGroup, String> categoriesGroupQueryMapper,
                                      Mapper<CategoriesModel, Categories> responseMapper,
                                      String fallbackCategoryGroup,
                                      RemoteService service) {

        this.categoriesGroupQueryMapper = categoriesGroupQueryMapper;
        this.fallbackCategoryGroup = fallbackCategoryGroup;
        this.responseMapper = responseMapper;
        this.service = service;
    }

    @Override
    public Observable<Optional<Categories>> getCategoriesForGroup(CategoriesGroup categoriesGroup) {
        return service.getCategories(categoriesGroupQueryMapper.transform(categoriesGroup).orElse(fallbackCategoryGroup))
                .map(responseMapper::transform);
    }
}

