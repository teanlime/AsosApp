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
    CategoriesPresenter(GetCategoriesUseCase getCategoriesUseCase,
                        EmptyCategoriesView emptyCategoriesView) {
        super(emptyCategoriesView);
        this.getCategoriesUseCase = getCategoriesUseCase;
    }

    @Override
    public void attachView(CategoriesView view) {
        super.attachView(view);

        this.view.initViews();
        this.view.displayLoading();

        requestCategories();
    }

    private void requestCategories() {
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
                view.addNavigationDrawerSubmenuCategories(model.getDescription(), model.getListing());
                view.displayContent();
                openNavigationDrawer();
            }
        });
    }

    private void openNavigationDrawer() {
        if (!view.isNavigationDrawerOpen()) {
            view.openNavigationDrawer();
        }
    }

    @Override
    public void detachView() {
        super.detachView();

        getCategoriesUseCase.cancel();
    }

    public void onBackPressed() {
        if (view.isNavigationDrawerOpen()) {
            view.closeNavigationDrawer();
        } else {
            view.processOnBackPressed();
        }
    }
}
