package com.teanlime.data.categories.model.repository.remote;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesRemoteRepositoryShould {

    private static final String FALLBACK = "test_fallback";

    private CategoriesRemoteRepository repository;
    private TestSubscriber<Optional<Categories>> subscriber = new TestSubscriber<>();

    @Mock
    private Mapper<CategoriesGroup, String> categoriesGroupQueryMapper;
    @Mock
    private Mapper<CategoriesModel, Categories> responseMapper;
    @Mock
    private CategoriesModel categoriesModel;
    @Mock
    private Categories categories;
    @Mock
    private RemoteService service;

    @Before
    public void setup() {
        givenRepository();
    }

    private void givenRepository() {
        repository = new CategoriesRemoteRepository(categoriesGroupQueryMapper, responseMapper, FALLBACK, service);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_categoriesGroupQueryMapper() {
        // when
        new CategoriesRemoteRepository(null, responseMapper, FALLBACK, service);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_responseMapper() {
        // when
        new CategoriesRemoteRepository(categoriesGroupQueryMapper, null, FALLBACK, service);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_FALLBACK() {
        // when
        new CategoriesRemoteRepository(categoriesGroupQueryMapper, responseMapper, null, service);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_service() {
        // when
        new CategoriesRemoteRepository(categoriesGroupQueryMapper, responseMapper, FALLBACK, null);
    }

    @Test
    public void use_fallback_category_if_no_category_provided() {
        // given
        givenResponseIsCorrect();

        // when
        repository.getCategoriesForGroup(null);

        // then
        verify(service).getCategories(FALLBACK);
        verifyNoMoreInteractions(service);
    }

    private void givenResponseIsCorrect() {
        when(categoriesGroupQueryMapper.map(any())).thenReturn(Optional.empty());
        when(service.getCategories(any())).thenReturn(Observable.just(categoriesModel));
        when(responseMapper.map(eq(categoriesModel))).thenReturn(Optional.of(categories));
    }

    @Test
    public void call_mapper_to_map_model_to_response() {
        // given
        givenResponseIsCorrect();

        // wen
        repository.getCategoriesForGroup(CategoriesGroup.MEN).subscribe(subscriber);

        // then
        verify(responseMapper).map(eq(categoriesModel));
        subscriber.assertReceivedOnNext(Collections.singletonList(Optional.of(categories)));
    }
}