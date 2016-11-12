package com.teanlime.data.favourites.model.repository.local;

import com.annimon.stream.Optional;
import com.teanlime.data.favourites.model.repository.LocalFavouritesRepository;
import com.teanlime.domain.categorylisting.model.response.Listings;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class InMemoryLocalFavouritesRepository implements LocalFavouritesRepository {

    private final List<Listings> favourites = new ArrayList<>();

    @Override
    public Observable<Optional<Void>> addFavourite(Listings listings) {
        if (!hasFavourite(listings)) {
            favourites.add(listings);
        }
        return null;
    }

    @Override
    public boolean hasFavourite(Listings listings) {
        return favourites.contains(listings);
    }

    @Override
    public Observable<Optional<List<Listings>>> getFavourites() {
        return Observable.just(Optional.of(favourites));
    }
}
