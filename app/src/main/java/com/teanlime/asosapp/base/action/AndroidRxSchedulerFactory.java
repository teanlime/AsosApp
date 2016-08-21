package com.teanlime.asosapp.base.action;

import com.teanlime.data.api.usecase.RxSchedulerFactory;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AndroidRxSchedulerFactory implements RxSchedulerFactory {

    @Override
    public Scheduler getExecutionScheduler() {
        return Schedulers.io();
    }

    @Override
    public Scheduler getPostExecutionScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
