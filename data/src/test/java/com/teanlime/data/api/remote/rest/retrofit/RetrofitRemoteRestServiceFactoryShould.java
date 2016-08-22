package com.teanlime.data.api.remote.rest.retrofit;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class RetrofitRemoteRestServiceFactoryShould {

    private RetrofitRemoteRestServiceFactory retrofitRemoteRestServiceFactory;

    @Before
    public void setup() {
        givenRetrofitServiceFactory();
    }

    private void givenRetrofitServiceFactory() {
        retrofitRemoteRestServiceFactory = new RetrofitRemoteRestServiceFactory();
    }

    @Test
    public void create_a_correct_RetrofitRemoteRestServiceFactory() {
        // when
        final RetrofitRemoteRestService retrofitRemoteRestService = retrofitRemoteRestServiceFactory.create();

        // then
        assertThat(retrofitRemoteRestService, CoreMatchers.notNullValue());
    }
}