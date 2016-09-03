package com.teanlime.asosapp.utils.di.dagger.qualifier.context;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
public @interface ApplicationContext {
}
