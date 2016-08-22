package com.teanlime.data.api.remote;

import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.categories.model.response.CategoriesModel;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DecoratedRetrofitRemoteServiceShould {

    private static final String CATEGORIES_QUERY = "categories_query";

    private DecoratedRetrofitRemoteService decoratedRetrofitRemoteService;

    @Mock
    private RetrofitRemoteRestService retrofitRemoteRestService;

    @Mock
    private Observable<CategoriesModel> delegatedObservable;

    @Before
    public void setup() {
        givenDecoratedRetrofitRemoteService();
    }

    private void givenDecoratedRetrofitRemoteService() {
        decoratedRetrofitRemoteService = new DecoratedRetrofitRemoteService(retrofitRemoteRestService);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_retrofitRemoteRestService() {
        // when
        new DecoratedRetrofitRemoteService(null);
    }

    @Test
    public void delegate_getCategories_call_to_retrofitRemoteRestService() {
        // when
        decoratedRetrofitRemoteService.getCategories(CATEGORIES_QUERY);

        // then
        verify(retrofitRemoteRestService).getCategories(same(CATEGORIES_QUERY));
    }

    @Test
    public void delegate_getCategories_response_from_retrofitRemoteRestService() {
        // given
        final Observable<CategoriesModel> delegatedResponse = givenRetrofitRemoteServiceReturnsObservable();

        // when
        final Observable<CategoriesModel> response = decoratedRetrofitRemoteService.getCategories(CATEGORIES_QUERY);

        // then
        assertThat(response, CoreMatchers.sameInstance(delegatedResponse));
    }

    private Observable<CategoriesModel> givenRetrofitRemoteServiceReturnsObservable() {
        when(retrofitRemoteRestService.getCategories(eq(CATEGORIES_QUERY))).thenReturn(delegatedObservable);
        return delegatedObservable;
    }
}