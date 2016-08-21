package com.teanlime.data.categories.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.model.response.Category;

import java.util.List;

public class CategoriesMapper implements Mapper<CategoriesModel, Categories> {

    private final ListMapper<CategoryModel, Category> categoryListMapper;
    private final List<Category> fallbackCategoryList;

    public CategoriesMapper(ListMapper<CategoryModel, Category> categoryListMapper,
                            List<Category> fallbackCategoryList) {

        this.fallbackCategoryList = fallbackCategoryList;
        this.categoryListMapper = categoryListMapper;
    }

    @Override
    public Optional<Categories> transform(CategoriesModel categoriesModel) {
        if (categoriesModel == null) {
            return Optional.empty();
        }
        return Optional.of(new Categories(
                categoriesModel.getDescription(),
                categoryListMapper.transform(categoriesModel.getListing()).orElse(fallbackCategoryList),
                categoriesModel.getSortType()));
    }
}
