package com.teanlime.domain.productdetails.usecase;

import com.teanlime.domain.api.usecase.UseCase;
import com.teanlime.domain.categorylisting.model.response.Listings;

public interface AddToFavouritesUseCase extends UseCase<Void, String> {

    AddToFavouritesUseCase listing(Listings listing);
}
