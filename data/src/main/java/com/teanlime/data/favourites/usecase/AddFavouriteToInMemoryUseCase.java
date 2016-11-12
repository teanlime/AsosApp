package com.teanlime.data.favourites.usecase;

import com.teanlime.data.api.usecase.RxUseCaseSubscription;
import com.teanlime.data.favourites.model.repository.LocalFavouritesRepository;
import com.teanlime.domain.api.usecase.UseCaseCallback;
import com.teanlime.domain.categorylisting.model.response.Listings;
import com.teanlime.domain.productdetails.usecase.AddToFavouritesUseCase;

public class AddFavouriteToInMemoryUseCase implements AddToFavouritesUseCase {

    private final RxUseCaseSubscription<Void, String> rxUseCaseSubscription;
    private final LocalFavouritesRepository localFavouritesRepository;

    private Listings listings;

    public AddFavouriteToInMemoryUseCase(RxUseCaseSubscription<Void, String> rxUseCaseSubscription,
                                         LocalFavouritesRepository localFavouritesRepository) {

        this.localFavouritesRepository = localFavouritesRepository;
        this.rxUseCaseSubscription = rxUseCaseSubscription;
    }

    @Override
    public AddToFavouritesUseCase listing(Listings listing) {
        this.listings = listing;
        return this;
    }

    @Override
    public void execute(UseCaseCallback<Void, String> callback) {
        rxUseCaseSubscription.subscribe(localFavouritesRepository.addFavourite(listings), callback);
    }

    @Override
    public void cancel() {
        // Do nothing, no network call here
    }
}
