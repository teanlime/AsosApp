package com.teanlime.data.api.usecase;

import rx.Scheduler;

public interface RxSchedulerFactory {

    Scheduler getExecutionScheduler();

    Scheduler getPostExecutionScheduler();
}
