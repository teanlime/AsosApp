package com.teanlime.data.api;

import com.teanlime.data.api.usecase.RxSchedulerFactory;

import rx.Scheduler;
import rx.schedulers.TestScheduler;

public class TestSchedulerFactory implements RxSchedulerFactory {

    private final TestScheduler postExecutionTestScheduler;
    private final TestScheduler executionTestScheduler;

    public TestSchedulerFactory(TestScheduler postExecutionTestScheduler,
                                TestScheduler executionTestScheduler) {

        this.postExecutionTestScheduler = postExecutionTestScheduler;
        this.executionTestScheduler = executionTestScheduler;
    }


    @Override
    public Scheduler getExecutionScheduler() {
        return executionTestScheduler;
    }

    @Override
    public Scheduler getPostExecutionScheduler() {
        return postExecutionTestScheduler;
    }
}
