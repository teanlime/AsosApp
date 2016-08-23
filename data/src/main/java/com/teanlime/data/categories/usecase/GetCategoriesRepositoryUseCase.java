package com.teanlime.data.categories.usecase;

import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.usecase.GetCategoriesUseCase;

public class GetCategoriesRepositoryUseCase implements GetCategoriesUseCase {

    private final RxUseCaseSubscription<Categories, String> rxUseCaseSubscription;
    private final CategoriesRepository categoriesRepository;

    private CategoriesGroup categoriesGroup;

    public GetCategoriesRepositoryUseCase(RxUseCaseSubscription<Categories, String> rxUseCaseSubscription,
                                          CategoriesRepository categoriesRepository) {

        this.rxUseCaseSubscription = rxUseCaseSubscription;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void execute(UseCaseCallback<Categories, String> callback) {
        rxUseCaseSubscription.subscribe(categoriesRepository.getCategoriesForGroup(categoriesGroup), callback);
    }

    @Override
    public void cancel() {
        rxUseCaseSubscription.cancel();
    }

    @Override
    public GetCategoriesUseCase categoriesGroup(CategoriesGroup categoriesGroup) {
        this.categoriesGroup = categoriesGroup;
        return this;
    }
}
