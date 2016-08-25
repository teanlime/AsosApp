package com.teanlime.data.categories.model.request.mapper;

import com.annimon.stream.Optional;
import com.teanlime.domain.categories.model.request.CategoriesGroup;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CategoriesGroupMapperShould {

    private static final String WOMEN_PATH = "women";
    private static final String MEN_PATH = "men";

    private CategoriesGroupMapper categoriesGroupMapper;

    @Before
    public void setup() {
        givenCategoriesGroupMapper();
    }

    private void givenCategoriesGroupMapper() {
        categoriesGroupMapper = new CategoriesGroupMapper();
    }

    @Test
    public void return_empty_if_no_categories_group_to_map_from() {
        // when
        assertThat(categoriesGroupMapper.map(null), is(Optional.empty()));
    }

    @Test
    public void return_women_if_categories_group_is_women() {
        // when
        assertThat(categoriesGroupMapper.map(CategoriesGroup.WOMEN), is(Optional.of(WOMEN_PATH)));
    }

    @Test
    public void return_men_if_categories_group_is_men() {
        // when
        assertThat(categoriesGroupMapper.map(CategoriesGroup.MEN), is(Optional.of(MEN_PATH)));
    }
}