package com.teanlime.domain.api.presentation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PresenterShould {

    private static final String EMPTY_VIEW = "empty_view";
    private static final String REAL_VIEW = "real_view";

    private TestPresenter presenter;

    @Before
    public void setup() {
        givenPresenter();
    }

    private void givenPresenter() {
        presenter = new TestPresenter(EMPTY_VIEW);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_empty_view() {
        // when
        new TestPresenter(null);
    }

    @Test
    public void use_empty_view_after_creation() {
        // when
        final String view = presenter.getView();

        // then
        assertThat(view, is(EMPTY_VIEW));
    }

    @Test
    public void use_real_view_after_real_view_attached() {
        // given
        givenRealViewIsAttached();

        // when
        final String view = presenter.getView();

        // then
        assertThat(view, is(REAL_VIEW));
    }

    private void givenRealViewIsAttached() {
        presenter.attachView(REAL_VIEW);
    }

    @Test
    public void use_empty_view_after_real_view_detached() {
        // given
        givenRealViewIsDetached();

        // when
        final String view = presenter.getView();

        // then
        assertThat(view, is(EMPTY_VIEW));
    }

    private void givenRealViewIsDetached() {
        givenRealViewIsAttached();
        presenter.detachView();
    }

    private static final class TestPresenter extends Presenter<String> {

        private TestPresenter(String emptyView) {
            super(emptyView);
        }

        private String getView() {
            return view;
        }
    }
}