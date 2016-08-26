package com.teanlime.data.categories.model.response.mapper;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.ListMapper;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.categories.model.response.CategoriesModel;
import com.teanlime.data.categories.model.response.CategoryModel;
import com.teanlime.domain.categories.model.response.Categories;
import com.teanlime.domain.categories.model.response.Category;

import java.util.List;

import static com.teanlime.domain.api.util.Validate.nonNull;

/**
 * Maps CategoryModel object into Category object
 */
public class CategoriesMapper implements Mapper<CategoriesModel, Categories> {

    private final ListMapper<CategoryModel, Category> categoryListMapper;
    private final List<Category> fallbackCategoryList;

    public CategoriesMapper(ListMapper<CategoryModel, Category> categoryListMapper,
                            List<Category> fallbackCategoryList) {

        this.fallbackCategoryList = nonNull(fallbackCategoryList);
        this.categoryListMapper = nonNull(categoryListMapper);
    }

    @Override
    public Optional<Categories> map(CategoriesModel categoriesModel) {
        if (categoriesModel == null) {
            return Optional.empty();
        }
        return Optional.of(new Categories(
                categoriesModel.getDescription(),
                categoryListMapper.map(categoriesModel.getListing()).orElse(fallbackCategoryList),
                categoriesModel.getSortType()));
    }
}
