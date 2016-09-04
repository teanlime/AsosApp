package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallbackSubscriberShould {

    private static final String MODEL_IS_NULL_LOCALISED_ERROR_MESSAGE = "Model is null";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String MODEL = "model_string";

    private CallbackDelegateSubscriber<String, String> callbackDelegateSubscriber;

    @Mock
    private Mapper<Throwable, String> useCaseExceptionMapper;

    @Mock
    private UseCaseCallback<String, String> useCaseCallback;

    @Before
    public void setup() {
        givenCallbackSubscriber();
        givenErrorMapper();
    }

    private void givenCallbackSubscriber() {
        callbackDelegateSubscriber = new CallbackDelegateSubscriber<>(useCaseExceptionMapper, useCaseCallback);
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
    public void throw_NPE_when_created_without_useCaseCallback() {
        // when
        new CallbackDelegateSubscriber<>(useCaseExceptionMapper, null);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_useCaseExceptionMapper() {
        // when
        new CallbackDelegateSubscriber<>(null, useCaseCallback);
    }

    @Test
    public void set_useCaseCallback_as_useCaseCallback() {
        // when
        callbackDelegateSubscriber.onCompleted();

        // then
        verify(useCaseCallback).onCompleted();
        verifyNoMoreInteractions(useCaseCallback);
        verifyZeroInteractions(useCaseCallback);
    }

    @Test(expected = NullPointerException.class)
    public void remove_callback_once_completed() {
        // given
        final Subscriber<Optional<String>> subscriber = givenCallbackSubscriberWhichAlreadyCompleted();

        // when
        subscriber.onCompleted();

        // then
        verify(useCaseCallback, times(1)).onCompleted();
    }

    private Subscriber<Optional<String>> givenCallbackSubscriberWhichAlreadyCompleted() {
        callbackDelegateSubscriber.onCompleted();
        return callbackDelegateSubscriber;
    }

    @Test
    public void delegate_onCompleted_to_useCaseCallback() {
        // when
        callbackDelegateSubscriber.onCompleted();

        // then
        verify(useCaseCallback).onCompleted();
        verifyNoMoreInteractions(useCaseCallback);
    }

    @Test
    public void delegate_onNext_to_useCaseCallback_when_model_is_present() {
        // when
        callbackDelegateSubscriber.onNext(Optional.of(MODEL));

        // then
        verify(useCaseCallback).onNext(same(MODEL));
        verifyNoMoreInteractions(useCaseCallback);
    }

    @Test
    public void redirect_onNext_to_onError_when_model_is_not_present() {
        // when
        callbackDelegateSubscriber.onNext(Optional.empty());

        // then
        verify(useCaseCallback).onError(same(MODEL_IS_NULL_LOCALISED_ERROR_MESSAGE));
        verifyNoMoreInteractions(useCaseCallback);
    }

    @Test
    public void mapper_is_called_on_onError() {
        // given
        final IllegalArgumentException error = new IllegalArgumentException(ERROR_MESSAGE);

        // when
        callbackDelegateSubscriber.onError(error);

        // then
        verify(useCaseExceptionMapper).map(same(error));
    }

    @Test
    public void delegate_onError_when_error_can_be_mapped() {
        // when
        callbackDelegateSubscriber.onError(new IllegalArgumentException(ERROR_MESSAGE));

        // then
        verify(useCaseCallback).onError(same(ERROR_MESSAGE));
        verifyNoMoreInteractions(useCaseCallback);
    }

    @Test
    public void do_nothing_on_onError_when_error_cannot_be_mapped() {
        // when
        callbackDelegateSubscriber.onError(null);

        // then
        verifyZeroInteractions(useCaseCallback);
    }
}