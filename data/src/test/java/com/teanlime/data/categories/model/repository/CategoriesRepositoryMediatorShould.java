package com.teanlime.data.categories.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.data.categories.model.repository.local.CategoriesLocalRepository;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.observers.TestSubscriber;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesRepositoryMediatorShould {

    private CategoriesRepositoryMediator categoriesRepositoryMediator;
    private TestSubscriber<Optional<Categories>> subscriber = new TestSubscriber<>();
    @Mock
    private CategoriesLocalRepository localCategoriesRepository;
    @Mock
    private CategoriesRepository remoteCategoriesRepository;
    @Mock
    private Categories categories;

    @Before
    public void setup() {
        categoriesRepositoryMediator = new CategoriesRepositoryMediator(localCategoriesRepository,
                remoteCategoriesRepository);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_localCategoriesRepository() {
        new CategoriesRepositoryMediator(null, remoteCategoriesRepository);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_remoteCategoriesRepository() {
        new CategoriesRepositoryMediator(localCategoriesRepository, null);
    }

    @Test
    public void return_categories_from_local_repository_if_has_locally_cached_categories() {
        when(localCategoriesRepository.hasCategoriesForGroup(CategoriesGroup.WOMEN)).thenReturn(true);

        categoriesRepositoryMediator.getCategoriesForGroup(CategoriesGroup.WOMEN);

        verify(localCategoriesRepository).hasCategoriesForGroup(CategoriesGroup.WOMEN);
        verify(localCategoriesRepository).getCategoriesForGroup(CategoriesGroup.WOMEN);
        verifyNoMoreInteractions(localCategoriesRepository);
        verifyZeroInteractions(remoteCategoriesRepository);
    }

    @Test
    public void return_categories_from_remote_repository_if_does_not_have_cached_categories() {
        when(localCategoriesRepository.hasCategoriesForGroup(CategoriesGroup.WOMEN)).thenReturn(false);
        when(remoteCategoriesRepository.getCategoriesForGroup(CategoriesGroup.WOMEN))
                .thenReturn(Observable.just(Optional.of(categories)));

        categoriesRepositoryMediator.getCategoriesForGroup(CategoriesGroup.WOMEN);

        verify(localCategoriesRepository).hasCategoriesForGroup(CategoriesGroup.WOMEN);
        verify(localCategoriesRepository, times(0)).getCategoriesForGroup(any());
        verify(remoteCategoriesRepository).getCategoriesForGroup(CategoriesGroup.WOMEN);
    }

    @Test
    public void cache_remote_repository_response_if_successful() {
        when(localCategoriesRepository.hasCategoriesForGroup(CategoriesGroup.WOMEN)).thenReturn(false);
        when(remoteCategoriesRepository.getCategoriesForGroup(CategoriesGroup.WOMEN))
                .thenReturn(Observable.just(Optional.of(categories)));

        categoriesRepositoryMediator.getCategoriesForGroup(CategoriesGroup.WOMEN).subscribe(subscriber);

        subscriber.assertReceivedOnNext(singletonList(Optional.of(categories)));
        subscriber.assertNoErrors();
        verify(localCategoriesRepository).putCategoriesForGroup(CategoriesGroup.WOMEN, categories);
    }

    @Test
    public void not_cache_remote_repository_response_if_failed() {
        when(localCategoriesRepository.hasCategoriesForGroup(CategoriesGroup.WOMEN)).thenReturn(false);
        when(remoteCategoriesRepository.getCategoriesForGroup(CategoriesGroup.WOMEN))
                .thenReturn(Observable.just(Optional.empty()));

        categoriesRepositoryMediator.getCategoriesForGroup(CategoriesGroup.WOMEN).subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertReceivedOnNext(singletonList(Optional.empty()));
        verify(localCategoriesRepository, times(0)).putCategoriesForGroup(any(), any());
    }
}