package com.teanlime.data.categories.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.response.Category;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CategoryMapperShould {

    private static final String CATEGORY_ID = "some_id";
    private static final String NAME = "some_name";
    private static final Integer COUNT = 5;

    private CategoryMapper categoryMapper;

    @Before
    public void setup() {
        givenCategoryMapper();
    }

    private void givenCategoryMapper() {
        categoryMapper = new CategoryMapper();
    }

    @Test
    public void return_empty_if_CategoryModel_is_empty() {
        // when
        assertThat(categoryMapper.map(null), is(equalTo(Optional.empty())));
    }

    @Test
    public void map_CategoryModel_correctly_into_Category() {
        // when
        final Category category = categoryMapper.map(new CategoryModel(CATEGORY_ID, NAME, COUNT)).get();

        // then
        assertThat(category.getCategoryId(), is(equalTo(CATEGORY_ID)));
        assertThat(category.getName(), is(equalTo(NAME)));
        assertThat(category.getProductCount(), is(equalTo(COUNT)));
    }
}