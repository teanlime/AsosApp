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

    public void onCreate(String categoriesGroup) {
        this.view.setupViews();
        this.view.displayLoading();

        toggleInitialCategoriesGroup(categoriesGroup == null ? null : CategoriesGroup.valueOf(categoriesGroup));
    }

    private void toggleInitialCategoriesGroup(CategoriesGroup categoriesGroup) {
        if (categoriesGroup == null) {
            selectWomenCategory();
            return;
        }

        switch (categoriesGroup) {
            case WOMEN:
                selectWomenCategory();
                break;
            case MEN:
                selectMenCategory();
                break;
            default:
                selectWomenCategory();
                break;
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

    public void onWomenMenuButtonClicked() {
        selectWomenCategory();
    }

    private void selectWomenCategory() {
        view.selectWomenCategoryGroup();
        view.deselectMenCategoryGroup();
        requestCategories(CategoriesGroup.WOMEN);
    }

    private void requestCategories(CategoriesGroup categoriesGroup) {
        view.setSelectedCategoriesGroup(categoriesGroup.name());

        getCategoriesUseCase.categoriesGroup(categoriesGroup).execute(new UseCaseCallback<Categories, String>() {
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

    public void onMenMenuButtonClicked() {
        selectMenCategory();
    }

    private void selectMenCategory() {
        view.selectMenCategoryGroup();
        view.deselectWomenCategoryGroup();
        requestCategories(CategoriesGroup.MEN);
    }
}
