package com.teanlime.domain.categories.presentation;

import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.model.response.Category;
import com.teanlime.domain.categories.usecase.GetCategoriesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class HomePresenterShould {

    private static final String WOMEN_CATEGORIES_GROUP = "WOMEN";
    private static final String MEN_CATEGORIES_GROUP = "MEN";
    private static final String NO_CATEGORIES_GROUP = null;

    private static final boolean NO_ACTION_KEY = false;
    private static final boolean ACTION_KEY = true;

    private static final String ERROR = "Some_terrible_error";

    private final List<Category> categoryList = Arrays.asList(new Category("1", "1_name", 1),
            new Category("2", "2_name", 2));

    private final Categories response = new Categories(WOMEN_CATEGORIES_GROUP, categoryList, "sort");

    private HomePresenter homePresenter;

    @Mock
    private EmptyCategoriesView categoriesView;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GetCategoriesUseCase getCategoriesUseCase;

    @Before
    public void setup() {
        givenHomePresenter();
        givenGetCategoriesUseCase();
    }

    private void givenHomePresenter() {
        homePresenter = new HomePresenter(getCategoriesUseCase, categoriesView);
    }

    private void givenGetCategoriesUseCase() {
        when(getCategoriesUseCase.categoriesGroup(any())).thenReturn(getCategoriesUseCase);
        doNothing().when(getCategoriesUseCase).execute(any());
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_getCategoriesUseCase() {
        // when
        new HomePresenter(null, categoriesView);
    }

    @Test
    public void setupViews_and_displayLoading_in_onCreate() {
        // when
        homePresenter.onCreate(NO_CATEGORIES_GROUP);

        // then
        final InOrder inOrder = inOrder(categoriesView, categoriesView);
        inOrder.verify(categoriesView).setupViews();
        inOrder.verify(categoriesView).displayLoading();
    }

    @Test
    public void set_WOMEN_category_if_no_category_provided() {
        // when
        homePresenter.onCreate(NO_CATEGORIES_GROUP);

        // then
        verify(categoriesView).setSelectedCategoriesGroup(eq(WOMEN_CATEGORIES_GROUP));
        verify(categoriesView).selectWomenCategoryGroup();
        verify(categoriesView).deselectMenCategoryGroup();
        verify(categoriesView, times(0)).deselectWomenCategoryGroup();
        verify(categoriesView, times(0)).selectMenCategoryGroup();
        verify(getCategoriesUseCase).categoriesGroup(CategoriesGroup.WOMEN);
        verify(getCategoriesUseCase).execute(any());
    }

    @Test
    public void set_WOMEN_category_if_WOMEN_category_provided() {
        // when
        homePresenter.onCreate(WOMEN_CATEGORIES_GROUP);

        // then
        verify(categoriesView).setSelectedCategoriesGroup(eq(WOMEN_CATEGORIES_GROUP));
        verify(categoriesView).selectWomenCategoryGroup();
        verify(categoriesView).deselectMenCategoryGroup();
        verify(categoriesView, times(0)).deselectWomenCategoryGroup();
        verify(categoriesView, times(0)).selectMenCategoryGroup();
        verify(getCategoriesUseCase).categoriesGroup(CategoriesGroup.WOMEN);
        verify(getCategoriesUseCase).execute(any());
    }

    @Test
    public void set_MEN_category_if_MEN_category_provided() {
        // when
        homePresenter.onCreate(MEN_CATEGORIES_GROUP);

        // then
        verify(categoriesView).setSelectedCategoriesGroup(eq(MEN_CATEGORIES_GROUP));
        verify(categoriesView).deselectWomenCategoryGroup();
        verify(categoriesView).selectMenCategoryGroup();
        verify(categoriesView, times(0)).selectWomenCategoryGroup();
        verify(categoriesView, times(0)).deselectMenCategoryGroup();
        verify(getCategoriesUseCase).categoriesGroup(CategoriesGroup.MEN);
        verify(getCategoriesUseCase).execute(any());
    }

    @Test
    public void cancel_getCategoriesUseCase_on_detachView() {
        // when
        homePresenter.detachView();

        // then
        verify(getCategoriesUseCase).cancel();
        verifyNoMoreInteractions(getCategoriesUseCase);
    }

    @Test
    public void closeNavigationDrawer_if_open_in_onBackPressed() {
        // given
        givenNavigationDrawerIsOpen();

        // when
        homePresenter.onBackPressed();

        // then
        verify(categoriesView).isNavigationDrawerOpen();
        verify(categoriesView).closeNavigationDrawer();
        verifyNoMoreInteractions(categoriesView);
    }

    private void givenNavigationDrawerIsOpen() {
        when(categoriesView.isNavigationDrawerOpen()).thenReturn(true);
    }

    @Test
    public void process_onBackPressed_on_view_if_navigation_drawer_is_closed_in_onBackPressed() {
        // given
        givenNavigationDrawerIsClosed();

        // when
        homePresenter.onBackPressed();

        // then
        verify(categoriesView).isNavigationDrawerOpen();
        verify(categoriesView).processOnBackPressed();
        verifyNoMoreInteractions(categoriesView);
    }

    private void givenNavigationDrawerIsClosed() {
        when(categoriesView.isNavigationDrawerOpen()).thenReturn(false);
    }

    @Test
    public void select_women_category_when_navigation_drawer_option_selected() {
        // when
        homePresenter.onWomenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).setSelectedCategoriesGroup(eq(WOMEN_CATEGORIES_GROUP));
        verify(categoriesView).selectWomenCategoryGroup();
        verify(categoriesView).deselectMenCategoryGroup();
        verify(categoriesView, times(0)).deselectWomenCategoryGroup();
        verify(categoriesView, times(0)).selectMenCategoryGroup();
        verify(getCategoriesUseCase).categoriesGroup(CategoriesGroup.WOMEN);
        verify(getCategoriesUseCase).execute(any());
    }

    @Test
    public void select_men_category_when_navigation_drawer_option_selected() {
        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).setSelectedCategoriesGroup(eq(MEN_CATEGORIES_GROUP));
        verify(categoriesView).deselectWomenCategoryGroup();
        verify(categoriesView).selectMenCategoryGroup();
        verify(categoriesView, times(0)).selectWomenCategoryGroup();
        verify(categoriesView, times(0)).deselectMenCategoryGroup();
        verify(getCategoriesUseCase).categoriesGroup(CategoriesGroup.MEN);
        verify(getCategoriesUseCase).execute(any());
    }

    @Test
    public void displayError_when_get_categories_failed() {
        // given
        givenGetCategoriesUseCaseFailed();

        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).displayCategoriesError(ERROR);
        verify(categoriesView, times(0)).setNavigationDrawerSubmenuCategories(any(), any());
        verify(categoriesView, times(0)).displayContent();
        verify(categoriesView, times(0)).openNavigationDrawer();
    }

    private void givenGetCategoriesUseCaseFailed() {
        doAnswer(invocation -> {
            ((UseCaseCallback) invocation.getArguments()[0]).onError(ERROR);
            return null;
        }).when(getCategoriesUseCase).execute(any());
    }

    @Test
    public void process_data_when_get_categories_successful() {
        // given
        givenGetCategoriesUseCaseIsSuccessful();

        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView, times(0)).displayCategoriesError(any());
        verify(categoriesView).setNavigationDrawerSubmenuCategories(WOMEN_CATEGORIES_GROUP, categoryList);
        verify(categoriesView).displayContent();
        verify(categoriesView).openNavigationDrawer();
    }

    private void givenGetCategoriesUseCaseIsSuccessful() {
        doAnswer(invocation -> {
            ((UseCaseCallback) invocation.getArguments()[0]).onNext(response);
            return null;
        }).when(getCategoriesUseCase).execute(any());
    }

    @Test
    public void open_navigation_drawer_if_closed_when_get_categories_successful() {
        // given
        givenNavigationDrawerIsClosed();
        givenGetCategoriesUseCaseIsSuccessful();

        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).isNavigationDrawerOpen();
        verify(categoriesView).openNavigationDrawer();
    }

    @Test
    public void not_open_navigation_drawer_if_already_open_when_get_categories_successful() {
        // given
        givenNavigationDrawerIsOpen();
        givenGetCategoriesUseCaseIsSuccessful();

        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).isNavigationDrawerOpen();
        verify(categoriesView, times(0)).openNavigationDrawer();
    }

    @Test
    public void return_touch_event_absorbed_when_actionKey_pressed_in_onMenMenuButtonTouchEvent() {
        // when
        final boolean touchEventAbsorbed = homePresenter.onMenMenuButtonTouchEvent(ACTION_KEY);

        // then
        assertThat(touchEventAbsorbed, is(true));
    }

    @Test
    public void return_touch_event_not_absorbed_when_no_actionKey_pressed_in_onMenMenuButtonTouchEvent() {
        // when
        final boolean touchEventAbsorbed = homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        assertThat(touchEventAbsorbed, is(false));
    }

    @Test
    public void return_touch_event_absorbed_when_actionKey_pressed_in_onWomenMenuButtonTouchEvent() {
        // when
        final boolean touchEventAbsorbed = homePresenter.onWomenMenuButtonTouchEvent(ACTION_KEY);

        // then
        assertThat(touchEventAbsorbed, is(true));
    }

    @Test
    public void return_touch_event_not_absorbed_when_no_actionKey_pressed_in_onMoWomenMenuButtonTouchEvent() {
        // when
        final boolean touchEventAbsorbed = homePresenter.onWomenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        assertThat(touchEventAbsorbed, is(false));
    }

    @Test
    public void selectWomenCategoryGroup_without_lollipop_when_view_is_not_lollipop() {
        // given
        givenViewIsNotLollipop();

        // when
        homePresenter.onWomenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).selectWomenCategoryGroup();
        verify(categoriesView).deselectMenCategoryGroup();
        verify(categoriesView, times(0)).selectMenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).deselectMenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).selectWomenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).deselectWomenCategoryGroupLollipopExtra();
    }

    private void givenViewIsNotLollipop() {
        when(categoriesView.isLollipop()).thenReturn(false);
    }

    @Test
    public void selectMenCategoryGroup_without_lollipop_when_view_is_not_lollipop() {
        // given
        givenViewIsNotLollipop();

        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).selectMenCategoryGroup();
        verify(categoriesView).deselectWomenCategoryGroup();
        verify(categoriesView, times(0)).selectMenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).deselectMenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).selectWomenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).deselectWomenCategoryGroupLollipopExtra();
    }

    @Test
    public void selectWomenCategoryGroup_with_lollipop_when_view_is_lollipop() {
        // given
        givenViewIsLollipop();

        // when
        homePresenter.onWomenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).selectWomenCategoryGroup();
        verify(categoriesView).deselectMenCategoryGroup();
        verify(categoriesView, times(0)).selectMenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).deselectWomenCategoryGroupLollipopExtra();
        verify(categoriesView).selectWomenCategoryGroupLollipopExtra();
        verify(categoriesView).deselectMenCategoryGroupLollipopExtra();
    }

    private void givenViewIsLollipop() {
        when(categoriesView.isLollipop()).thenReturn(true);
    }

    @Test
    public void selectMenCategoryGroup_with_lollipop_when_view_is_lollipop() {
        // given
        givenViewIsLollipop();

        // when
        homePresenter.onMenMenuButtonTouchEvent(NO_ACTION_KEY);

        // then
        verify(categoriesView).selectMenCategoryGroup();
        verify(categoriesView).deselectWomenCategoryGroup();
        verify(categoriesView).selectMenCategoryGroupLollipopExtra();
        verify(categoriesView).deselectWomenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).selectWomenCategoryGroupLollipopExtra();
        verify(categoriesView, times(0)).deselectMenCategoryGroupLollipopExtra();
    }

    @Test
    public void close_navigation_drawer_when_failed_to_load_category_selected_from_menu() {
        // given
        givenGetCategoriesUseCaseFailed();
        givenNavigationDrawerIsOpen();

        // when
        homePresenter.onNavigationItemSelected(WOMEN_CATEGORIES_GROUP, 1);

        // then
        verify(categoriesView, times(0)).startCategoryFragment(any());
        verify(categoriesView).isNavigationDrawerOpen();
        verify(categoriesView).closeNavigationDrawer();
    }

    @Test
    public void close_navigation_drawer_and_start_selected_new_category_screen_when_category_selected_from_menu() {
        // given
        givenGetCategoriesUseCaseIsSuccessful();
        givenNavigationDrawerIsOpen();

        // when
        homePresenter.onNavigationItemSelected(WOMEN_CATEGORIES_GROUP, 1);

        // then
        verify(categoriesView).startCategoryFragment(categoryList.get(1).getCategoryId());
        verify(categoriesView).isNavigationDrawerOpen();
        verify(categoriesView).closeNavigationDrawer();
    }
}