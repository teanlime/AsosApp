package com.teanlime.data.api.usecase;

import rx.Scheduler;

/**
 * Provides instances of relevant execution schedulers for the purpose of RXJava operations
 * Each scheduler can be it's own thread
 */
public interface RxSchedulerFactory {

    /**
     * Provides an execution scheduler, which is the scheduler and thread the RX operations will be performed on
     *
     * @return execution scheduler
     */
    Scheduler getExecutionScheduler();

    /**
     * Provides a post-execution scheduler, which is the scheduler and thread the result of the RX operations will be
     * pushed out on
     *
     * @return post-execution scheduler
     */
    Scheduler getPostExecutionScheduler();
}
