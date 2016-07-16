package com.teanlime.data.categories.usecase;

import com.teanlime.data.api.usecase.CallbackSubscriber;
import com.teanlime.data.api.usecase.RepositoryUseCase;
import com.teanlime.data.api.usecase.RxSchedulerFactory;
import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.usecase.GetCategoriesUseCase;

import rx.Observable;

public class GetCategoriesRepositoryUseCase extends RepositoryUseCase<Categories, String> implements GetCategoriesUseCase {

    private final CategoriesRepository categoriesRepository;

    private CategoriesGroup categoriesGroup;

    protected GetCategoriesRepositoryUseCase(CallbackSubscriber<Categories, String> callbackSubscriber,
                                             CategoriesRepository categoriesRepository,
                                             RxSchedulerFactory schedulerFactory) {
        super(callbackSubscriber, schedulerFactory);
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    protected Observable<Categories> getData() {
        return categoriesRepository.getCategoriesForGroup(categoriesGroup);
    }

    @Override
    public void categoriesGroup(CategoriesGroup categoriesGroup) {
        this.categoriesGroup = categoriesGroup;
    }
}