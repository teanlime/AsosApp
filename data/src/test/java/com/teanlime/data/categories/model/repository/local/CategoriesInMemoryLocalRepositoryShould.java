package com.teanlime.data.categories.model.repository.local;

import com.annimon.stream.Optional;
import com.teanlime.domain.categories.model.request.CategoriesGroup;
import com.teanlime.domain.categories.model.response.Categories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.observers.TestSubscriber;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesInMemoryLocalRepositoryShould {

    private final TestSubscriber<Optional<Categories>> subscriber = new TestSubscriber<>();

    private CategoriesInMemoryLocalRepository repository;

    @Mock
    private Categories womenCategories;

    @Mock
    private Categories menCategories;

    @Mock
    private Categories alternativeMenCategories;

    @Before
    public void setup() {
        repository = new CategoriesInMemoryLocalRepository();
    }

    @Test
    public void add_a_category_correctly() {
        // when
        repository.putCategoriesForGroup(CategoriesGroup.MEN, menCategories);

        // then
        assertThat(repository.hasCategoriesForGroup(CategoriesGroup.MEN), is(true));
        assertThat(repository.hasCategoriesForGroup(CategoriesGroup.WOMEN), is(false));
    }

    @Test
    public void add_multiple_categories_correctly() {
        // when
        repository.putCategoriesForGroup(CategoriesGroup.MEN, menCategories);
        repository.putCategoriesForGroup(CategoriesGroup.WOMEN, womenCategories);

        // then
        assertThat(repository.hasCategoriesForGroup(CategoriesGroup.MEN), is(true));
        assertThat(repository.hasCategoriesForGroup(CategoriesGroup.WOMEN), is(true));
    }

    @Test
    public void return_a_category_correctly() {
        // given
        repository.putCategoriesForGroup(CategoriesGroup.MEN, menCategories);

        // when
        repository.getCategoriesForGroup(CategoriesGroup.MEN).subscribe(subscriber);

        // then
        subscriber.assertReceivedOnNext(singletonList(Optional.of(menCategories)));
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
    }

    @Test
    public void override_a_category_correctly() {
        // given
        repository.putCategoriesForGroup(CategoriesGroup.MEN, menCategories);
        repository.putCategoriesForGroup(CategoriesGroup.MEN, alternativeMenCategories);

        // when
        repository.getCategoriesForGroup(CategoriesGroup.MEN).subscribe(subscriber);

        // then
        subscriber.assertReceivedOnNext(singletonList(Optional.of(alternativeMenCategories)));
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
    }

    @Test
    public void return_error_when_no_category_found() {
        // when
        repository.getCategoriesForGroup(CategoriesGroup.MEN).subscribe(subscriber);

        // then
        subscriber.assertNoValues();
        subscriber.assertError(NullPointerException.class);
    }

    @Test
    public void erase_a_category_correctly() {
        // given
        repository.putCategoriesForGroup(CategoriesGroup.MEN, menCategories);
        repository.hasCategoriesForGroup(CategoriesGroup.MEN);

        // when
        repository.eraseCategoryForGroup(CategoriesGroup.MEN);

        // then
        assertThat(repository.hasCategoriesForGroup(CategoriesGroup.MEN), is(false));
    }
}