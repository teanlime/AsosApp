package com.teanlime.data.categories.model.mapper;

import com.teanlime.data.api.ListMapper;
import com.teanlime.data.api.Mapper;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.Categories;
import com.teanlime.domain.categories.model.Category;

public class CategoriesMapper implements Mapper<CategoriesModel, Categories> {

    private final ListMapper<CategoryModel, Category> categoryListMapper;

    public CategoriesMapper(ListMapper<CategoryModel, Category> categoryListMapper) {
        this.categoryListMapper = categoryListMapper;
    }

    @Override
    public Categories transform(CategoriesModel categoriesModel) {
        if (categoriesModel == null) {
            return null;
        }
        return new Categories(
                categoriesModel.getDescription(),
                categoryListMapper.transform(categoriesModel.getListing()),
                categoriesModel.getSortType()
        );
    }
}
