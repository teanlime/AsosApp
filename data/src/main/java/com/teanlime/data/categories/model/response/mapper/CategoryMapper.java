package com.teanlime.data.categories.model.response.mapper;

import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.response.Category;

public class CategoryMapper implements Mapper<CategoryModel, Category> {

    @Override
    public Category transform(CategoryModel from) {
        if (from == null) {
            return null;
        }
        return new Category(from.getCategoryId(), from.getName(), from.getProductCount());
    }
}
