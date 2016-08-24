package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.EmptyUseCaseCallback;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallbackSubscriberShould {

    private static final String MODEL_IS_NULL_LOCALISED_ERROR_MESSAGE = "Model is null";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String MODEL = "model_string";

    private CallbackSubscriber<String, String> callbackSubscriber;

    @Mock
    private EmptyUseCaseCallback<String, String> emptyUseCaseCallback;

    @Mock
    private UseCaseCallback<String, String> realUseCaseCallback;

    @Mock
    private Mapper<Throwable, String> useCaseExceptionMapper;

    @Before
    public void setup() {
        givenCallbackSubscriber();
        givenErrorMapper();
    }

    private void givenCallbackSubscriber() {
        callbackSubscriber = new CallbackSubscriber<>(emptyUseCaseCallback, useCaseExceptionMapper);
    }

    private void givenErrorMapper() {
        when(useCaseExceptionMapper.map(any(Throwable.class))).thenAnswer(new Answer<Optional<String>>() {
            @Override
            public Optional<String> answer(InvocationOnMock invocation) throws Throwable {
                final Throwable throwable = (Throwable) invocation.getArguments()[0];
                return throwable == null ? Optional.empty() : Optional.of(throwable.getLocalizedMessage());
            }
        });
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_emptyUseCaseCallback() {
        // when
        new CallbackSubscriber<>(null, useCaseExceptionMapper);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_useCaseExceptionMapper() {
        // when
        new CallbackSubscriber<>(emptyUseCaseCallback, null);
    }

    @Test
    public void set_emptyUseCaseCallback_as_useCaseCallback() {
        // when
        callbackSubscriber.onCompleted();

        // then
        verify(emptyUseCaseCallback).onCompleted();
        verifyNoMoreInteractions(emptyUseCaseCallback);
        verifyZeroInteractions(realUseCaseCallback);
    }

    @Test
    public void use_emptyUseCaseCallback_when_setting_null_callback() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithNullCallbackSet();

        // when
        callbackSubscriber.onCompleted();

        // then
        verify(emptyUseCaseCallback).onCompleted();
        verifyNoMoreInteractions(emptyUseCaseCallback);
        verifyZeroInteractions(realUseCaseCallback);
    }

    private CallbackSubscriber<String, String> givenCallbackSubscriberWithNullCallbackSet() {
        return callbackSubscriber.callback(null);
    }

    @Test
    public void set_new_callback_correctly() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onCompleted();

        // then
        verify(realUseCaseCallback).onCompleted();
        verifyNoMoreInteractions(realUseCaseCallback);
        verifyZeroInteractions(emptyUseCaseCallback);
    }

    private CallbackSubscriber<String, String> givenCallbackSubscriberWithRealCallback() {
        return callbackSubscriber.callback(realUseCaseCallback);
    }


    @Test
    public void return_this_correctly_in_callback() {
        // when
        final CallbackSubscriber returnedCallbackSubscriber = callbackSubscriber.callback(realUseCaseCallback);

        // then
        assertThat(returnedCallbackSubscriber, is(notNullValue()));
        assertThat(returnedCallbackSubscriber, is(sameInstance(callbackSubscriber)));
    }

    @Test
    public void use_emptyUseCaseCallback_when_removing_callback() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onCompleted();
        callbackSubscriber.onCompleted();

        // then
        // Real use case used first time
        verify(realUseCaseCallback).onCompleted();
        // Empty use case used next time
        verify(emptyUseCaseCallback).onCompleted();
        verifyNoMoreInteractions(emptyUseCaseCallback);
    }

    @Test
    public void delegate_onCreate_to_useCaseCallback() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onCompleted();

        // then
        verify(realUseCaseCallback).onCompleted();
        verifyNoMoreInteractions(realUseCaseCallback);
        verifyZeroInteractions(emptyUseCaseCallback);
    }

    @Test
    public void delegate_onNext_to_useCaseCallback_when_model_is_present() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onNext(Optional.of(MODEL));

        // then
        verify(realUseCaseCallback).onNext(same(MODEL));
        verifyNoMoreInteractions(realUseCaseCallback);
        verifyZeroInteractions(emptyUseCaseCallback);
    }

    @Test
    public void redirect_onNext_to_onError_when_model_is_not_present() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onNext(Optional.empty());

        // then
        verify(realUseCaseCallback).onError(same(MODEL_IS_NULL_LOCALISED_ERROR_MESSAGE));
        verifyNoMoreInteractions(realUseCaseCallback);
        verifyZeroInteractions(emptyUseCaseCallback);
    }

    @Test
    public void mapper_is_called_on_onError() {
        // given
        final IllegalArgumentException error = new IllegalArgumentException(ERROR_MESSAGE);

        // when
        callbackSubscriber.onError(error);

        // then
        verify(useCaseExceptionMapper).map(same(error));
    }

    @Test
    public void delegate_onError_when_error_can_be_mapped() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onError(new IllegalArgumentException(ERROR_MESSAGE));

        // then
        verify(realUseCaseCallback).onError(same(ERROR_MESSAGE));
        verifyNoMoreInteractions(realUseCaseCallback);
        verifyZeroInteractions(emptyUseCaseCallback);
    }

    @Test
    public void do_nothing_on_onError_when_error_cannot_be_mapped() {
        // given
        final CallbackSubscriber<String, String> callbackSubscriber = givenCallbackSubscriberWithRealCallback();

        // when
        callbackSubscriber.onError(null);

        // then
        verifyZeroInteractions(realUseCaseCallback);
        verifyZeroInteractions(emptyUseCaseCallback);
    }
}