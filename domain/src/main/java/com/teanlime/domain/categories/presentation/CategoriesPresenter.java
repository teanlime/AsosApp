package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.api.presentation.Presenter;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.usecase.GetCategoriesUseCase;

import javax.inject.Inject;

public class CategoriesPresenter extends Presenter<CategoriesView> {

    private final GetCategoriesUseCase getCategoriesUseCase;

    @Inject
    public CategoriesPresenter(GetCategoriesUseCase getCategoriesUseCase,
                               EmptyCategoriesView emptyCategoriesView) {
        super(emptyCategoriesView);
        this.getCategoriesUseCase = getCategoriesUseCase;
    }

    @Override
    public void attachView(CategoriesView view) {
        super.attachView(view);

        //TODO Change!
        getCategoriesUseCase.categoriesGroup(CategoriesGroup.WOMEN).execute(new UseCaseCallback<Categories, String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(String exception) {
                view.displayCategoriesError(exception);
            }

            @Override
            public void onNext(Categories model) {
                view.displayCategories(model);
            }
        });
    }
}
