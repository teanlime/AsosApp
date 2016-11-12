package com.teanlime.data.favourites.model.repository;

import com.annimon.stream.Optional;
import com.teanlime.domain.categorylisting.model.response.Listings;

import java.util.List;

import rx.Observable;

public interface LocalFavouritesRepository {

    Observable<Optional<Void>> addFavourite(Listings listings);

    boolean hasFavourite(Listings listings);

    Observable<Optional<List<Listings>>> getFavourites();
}
