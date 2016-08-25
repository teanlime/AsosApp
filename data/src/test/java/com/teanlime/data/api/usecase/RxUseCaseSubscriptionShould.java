package com.teanlime.data.api.usecase;

import com.annimon.stream.Optional;
import com.teanlime.data.api.TestSchedulerFactory;
import com.teanlime.domain.api.usecase.UseCaseCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RxUseCaseSubscriptionShould {

    private final TestSubscriber<Optional<String>> subscriber = new TestSubscriber<>();
    private final TestScheduler postExecutionTestScheduler = Schedulers.test();
    private final TestScheduler executionTestScheduler = Schedulers.test();

    private RxUseCaseSubscription<String, Integer> rxUseCaseSubscription;

    @Mock
    private CallbackSubscriber<String, Integer> callbackSubscriber;
    @Mock
    private UseCaseCallback<String, Integer> useCaseCallback;


    @Before
    public void setup() {
        rxUseCaseSubscription = new RxUseCaseSubscription<>(callbackSubscriber, getTestSchedulerFactory());
        givenSubscriber();
    }

    private TestSchedulerFactory getTestSchedulerFactory() {
        return new TestSchedulerFactory(postExecutionTestScheduler, executionTestScheduler);
    }

    private void givenSubscriber() {
        when(callbackSubscriber.callback(any())).thenReturn(subscriber);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_subscriber() {
        // when
        new RxUseCaseSubscription<>(null, mock(RxSchedulerFactory.class));
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_scheduler() {
        // when
        new RxUseCaseSubscription<>(callbackSubscriber, null);
    }

    @Test
    public void subscribe_on_correct_execution_thread() {
        // given
        rxUseCaseSubscription.subscribe(Observable.just(Optional.of("simple")), useCaseCallback);
        subscriber.assertNoTerminalEvent();
        subscriber.assertNotCompleted();

        // when
        executionTestScheduler.triggerActions();

        // then
        subscriber.assertNoTerminalEvent();
        subscriber.assertNotCompleted();
    }

    @Test
    public void subscribe_on_correct_post_execution_thread() {
        // given
        rxUseCaseSubscription.subscribe(Observable.just(Optional.of("simple")), useCaseCallback);
        executionTestScheduler.triggerActions();

        // when
        postExecutionTestScheduler.triggerActions();

        // then
        subscriber.assertTerminalEvent();
        subscriber.assertCompleted();
    }

    @Test
    public void return_correct_values() {
        // given
        final Optional<String> first = Optional.of("first");
        final Optional<String> second = Optional.of("second");
        final Observable<Optional<String>> observable = Observable.from(Arrays.asList(first, second));

        // when
        rxUseCaseSubscription.subscribe(observable, useCaseCallback);
        executionTestScheduler.triggerActions();
        postExecutionTestScheduler.triggerActions();

        // then
        subscriber.assertReceivedOnNext(Arrays.asList(first, second));
    }

    @Test
    public void set_callback_on_callback_subscriber() {
        // given
        final Optional<String> first = Optional.of("first");

        // when
        rxUseCaseSubscription.subscribe(Observable.just(first), useCaseCallback);

        // then
        verify(callbackSubscriber).callback(useCaseCallback);
    }

    @Test
    public void un_subscribe_on_cancel() {
        // given
        final Optional<String> first = Optional.of("first");
        rxUseCaseSubscription.subscribe(Observable.just(first), useCaseCallback);
        assertThat(subscriber.isUnsubscribed(), is(false));

        // when
        rxUseCaseSubscription.cancel();

        // then
        assertThat(subscriber.isUnsubscribed(), is(true));
    }
}