package com.teanlime.data.categories.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.model.response.Category;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesMapperShould {

    private static final List<CategoryModel> REAL_MODEL_LIST = Arrays.asList(mock(CategoryModel.class), mock
            (CategoryModel.class));
    private static final List<Category> REAL_LIST = Arrays.asList(mock(Category.class), mock(Category.class));
    private static final List<Category> FALLBACK_LIST = Collections.singletonList(mock(Category.class));
    private static final String DESCRIPTION = "testing_description";
    private static final String SORT_TYPE = "testing_sort_type";

    private CategoriesMapper categoriesMapper;

    @Mock
    private ListMapper<CategoryModel, Category> categoryListMapper;

    @Before
    public void setup() {
        givenCategoriesMapper();
    }

    private void givenCategoriesMapper() {
        categoriesMapper = new CategoriesMapper(categoryListMapper, FALLBACK_LIST);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_categoryListMapper() {
        // when
        new CategoriesMapper(null, FALLBACK_LIST);
    }

    @Test(expected = NullPointerException.class)
    public void throw_NPE_when_created_without_fallbackCategoryList() {
        // when
        new CategoriesMapper(categoryListMapper, null);
    }

    @Test
    public void map_null_categories_model_to_empty() {
        // when
        assertThat(categoriesMapper.map(null), is(Optional.empty()));
    }

    @Test
    public void map_categories_model_correctly_to_categories() {
        // given
        givenListMapperReturnsCorrectList();

        // when
        final Categories categories = categoriesMapper.map(new CategoriesModel(DESCRIPTION, REAL_MODEL_LIST,
                SORT_TYPE)).get();

        // then
        assertThat(categories, is(notNullValue()));
        assertThat(categories.getDescription(), is(equalTo(DESCRIPTION)));
        assertThat(categories.getListing(), is(equalTo(REAL_LIST)));
        assertThat(categories.getListing().size(), is(equalTo(REAL_LIST.size())));
        assertThat(categories.getSortType(), is(equalTo(SORT_TYPE)));
    }

    private void givenListMapperReturnsCorrectList() {
        when(categoryListMapper.map(REAL_MODEL_LIST)).thenReturn(Optional.of(REAL_LIST));
    }

    @Test
    public void use_fallback_list_when_category_list_is_null() {
        // given
        givenListMapperReturnsEmptyList();

        // when
        final Categories categories = categoriesMapper.map(new CategoriesModel(DESCRIPTION, null,
                SORT_TYPE)).get();

        // then
        assertThat(categories, is(notNullValue()));
        assertThat(categories.getDescription(), is(equalTo(DESCRIPTION)));
        assertThat(categories.getListing(), is(equalTo(FALLBACK_LIST)));
        assertThat(categories.getListing().size(), is(equalTo(FALLBACK_LIST.size())));
        assertThat(categories.getSortType(), is(equalTo(SORT_TYPE)));
    }

    private void givenListMapperReturnsEmptyList() {
        when(categoryListMapper.map(any())).thenReturn(Optional.empty());
    }
}