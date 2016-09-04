package com.teanlime.asosapp.home.model;

import com.teanlime.asosapp.base.model.ActivityComponentFactory;
import com.teanlime.asosapp.base.presentation.AsosActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@SuppressWarnings("ConstantConditions")
@RunWith(MockitoJUnitRunner.class)
public class ActivityComponentFlyweightShould {

    private static final BigDecimal CURRENT_COMPONENT = BigDecimal.ZERO;
    private static final BigDecimal SAVED_COMPONENT = BigDecimal.TEN;
    private static final BigDecimal NEW_COMPONENT = BigDecimal.ONE;
    private static final BigDecimal NO_SAVED_COMPONENT = null;

    private ActivityComponentFlyweight<BigDecimal> activityComponentFlyweight;

    @Mock
    private ActivityComponentFactory<BigDecimal> activityComponentFactory;

    @Mock
    private AsosActivity<BigDecimal> activity;

    @Before
    public void setup() {
        givenActivityComponentFlyweight();
    }

    private void givenActivityComponentFlyweight() {
        activityComponentFlyweight = new ActivityComponentFlyweight<>(activityComponentFactory);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_activityComponentFactory() {
        // when
        new ActivityComponentFlyweight<>(null);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_no_activity_provided() {
        // when
        activityComponentFlyweight.create(null);
    }

    @Test
    public void return_new_component_when_no_component_is_saved() {
        // given
        givenNoComponentIsSaved();

        // when
        final BigDecimal component = activityComponentFlyweight.create(activity);

        // then
        verify(activityComponentFactory).create(activity);
        assertThat(component, is(equalTo(NEW_COMPONENT)));
    }

    private void givenNoComponentIsSaved() {
        when(activity.getLastCustomNonConfigurationInstance()).thenReturn(NO_SAVED_COMPONENT);
        when(activityComponentFactory.create(activity)).thenReturn(NEW_COMPONENT);
    }

    @Test
    public void return_saved_component_when_valid_saved_component_exists() {
        // given
        givenValidComponentIsSaved();

        // when
        final BigDecimal component = activityComponentFlyweight.create(activity);

        // then
        verifyZeroInteractions(activityComponentFactory);
        assertThat(component, is(equalTo(SAVED_COMPONENT)));
    }

    private void givenValidComponentIsSaved() {
        when(activity.getLastCustomNonConfigurationInstance()).thenReturn(SAVED_COMPONENT);
    }

    @Test
    public void return_current_component_when_valid_component_exists() {
        // given
        givenValidCurrentComponent();

        // when
        final BigDecimal component = activityComponentFlyweight.create(activity);

        // then
        verifyZeroInteractions(activityComponentFactory);
        assertThat(component, is(equalTo(CURRENT_COMPONENT)));
    }

    private void givenValidCurrentComponent() {
        when(activity.getActivityComponent()).thenReturn(CURRENT_COMPONENT);
    }
}