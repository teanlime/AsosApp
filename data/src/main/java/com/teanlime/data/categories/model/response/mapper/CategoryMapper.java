package com.teanlime.data.categories.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.response.Category;

/**
 * Maps CategoryModel object into Category
 */
public class CategoryMapper implements Mapper<CategoryModel, Category> {

    @Override
    public Optional<Category> map(CategoryModel from) {
        if (from == null) {
            return Optional.empty();
        }
        return Optional.of(new Category(from.getCategoryId(), from.getName(), from.getProductCount()));
    }
}
