package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Subscriber;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CallbackSubscriberFactoryShould {

    private CallbackDelegateSubscriberFactory<String, Integer> callbackDelegateSubscriberFactory;

    @Mock
    private Mapper<Throwable, Integer> useCaseExceptionMapper;

    @Mock
    private UseCaseCallback<String, Integer> useCaseCallback;

    @Before
    public void setup() {
        givenCallbackDelegateSubscriberFactory();
    }

    private void givenCallbackDelegateSubscriberFactory() {
        callbackDelegateSubscriberFactory = new CallbackDelegateSubscriberFactory<>(useCaseExceptionMapper);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_useCaseExceptionMapper() {
        // when
        new CallbackDelegateSubscriberFactory<>(null);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_creating_subscriber_without_callback() {
        // when
        callbackDelegateSubscriberFactory.create(null);
    }

    @Test
    public void create_correct_Subscriber() {
        // when
        final Subscriber<Optional<String>> subscriber = callbackDelegateSubscriberFactory.create(useCaseCallback);

        // then
        assertThat(subscriber, is(notNullValue()));
        assertThat(subscriber, is(instanceOf(CallbackDelegateSubscriber.class)));
    }
}