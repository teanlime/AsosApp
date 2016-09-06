package com.teanlime.data.categorylisting.model.repository.remote;

import com.annimon.stream.Optional;
import com.teanlime.data.api.mapper.Mapper;
import com.teanlime.data.api.remote.RemoteService;
import com.teanlime.data.categorylisting.model.repository.CategoryListingRepository;
import com.teanlime.data.categorylisting.model.response.CategoryListingModel;
import com.teanlime.domain.categorylisting.model.response.CategoryListing;

import javax.inject.Inject;

import rx.Observable;

public class CategoryListingRemoteRepository implements CategoryListingRepository {

    private final Mapper<CategoryListingModel, CategoryListing> categoryListingMapper;
    private final RemoteService service;
    private final String categoryId;

    @Inject
    public CategoryListingRemoteRepository(Mapper<CategoryListingModel, CategoryListing> categoryListingMapper,
                                           RemoteService service,
                                           String categoryId) {

        this.categoryListingMapper = categoryListingMapper;
        this.categoryId = categoryId;
        this.service = service;
    }


    @Override
    public Observable<Optional<CategoryListing>> getCategoryListing() {
        return service.getCategoryListing(categoryId).map(categoryListingMapper::map);
    }
}

