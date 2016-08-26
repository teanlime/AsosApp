package com.teanlime.data.categories.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.categories.model.repository.CategoriesRepository;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetCategoriesRepositoryUseCaseShould {

    private GetCategoriesRepositoryUseCase getCategoriesRepositoryUseCase;
    @Mock
    private RxUseCaseSubscription<Categories, String> rxUseCaseSubscription;
    @Mock
    private UseCaseCallback<Categories, String> callback;
    @Mock
    private Observable<Optional<Categories>> observable;
    @Mock
    private CategoriesRepository categoriesRepository;

    @Before
    public void setup() {
        givenGetCategoriesRepositoryUseCase();
    }

    private void givenGetCategoriesRepositoryUseCase() {
        getCategoriesRepositoryUseCase = new GetCategoriesRepositoryUseCase(rxUseCaseSubscription,
                categoriesRepository);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_rxUseCaseSubscription() {
        // when
        new GetCategoriesRepositoryUseCase(null, categoriesRepository);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_categoriesRepository() {
        // when
        new GetCategoriesRepositoryUseCase(rxUseCaseSubscription, null);
    }

    @Test
    public void call_categoryRepository_with_a_given_group() {
        // given
        givenCategoriesRepository();

        // when
        getCategoriesRepositoryUseCase.execute(callback);

        // then
        verify(categoriesRepository).getCategoriesForGroup(CategoriesGroup.WOMEN);
        verifyNoMoreInteractions(categoriesRepository);
    }

    private void givenCategoriesRepository() {
        getCategoriesRepositoryUseCase.categoriesGroup(CategoriesGroup.WOMEN);
        when(categoriesRepository.getCategoriesForGroup(any())).thenReturn(observable);

    }

    @Test
    public void call_useCaseSubscription_to_subscribe() {
        // given
        givenCategoriesRepository();

        // when
        getCategoriesRepositoryUseCase.execute(callback);

        // then
        verify(rxUseCaseSubscription).subscribe(observable, callback);
        verifyNoMoreInteractions(rxUseCaseSubscription);
    }

    @Test
    public void cancel_subscription_on_cancel() {
        // when
        getCategoriesRepositoryUseCase.cancel();

        // then
        verify(rxUseCaseSubscription).cancel();
        verifyNoMoreInteractions(rxUseCaseSubscription);
    }

    @Test
    public void return_this_correctly_in_categoriesGroup() {
        assertThat(getCategoriesRepositoryUseCase.categoriesGroup(CategoriesGroup.WOMEN),
                is(getCategoriesRepositoryUseCase));
    }
}