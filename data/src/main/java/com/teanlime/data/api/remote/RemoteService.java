package com.teanlime.data.api.remote;

import com.teanlime.data.categories.model.response.CategoriesModel;

import rx.Observable;

/**
 * Fetches data from remote source (for example, Shared Preferences, Network etc)
 */
public interface RemoteService {

    /**
     * Returns an Observable of all clothing categories for a given category group (either Women or Men)
     *
     * @param categoriesGroupQuery super category group (for example men or women)
     * @return a list of available categories for specified group
     */
    Observable<CategoriesModel> getCategories(String categoriesGroupQuery);
}
