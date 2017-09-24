package com.astro.guide.features.favourite.presenter.impl;

import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.impl.BasePresenterImpl;
import com.astro.guide.features.favourite.interactor.FavouritesListInteractor;
import com.astro.guide.features.favourite.presenter.FavouritesListPresenter;
import com.astro.guide.features.favourite.view.FavouritesListView;

import javax.inject.Inject;

public final class FavouritesListPresenterImpl extends BasePresenterImpl<FavouritesListView> implements FavouritesListPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final FavouritesListInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public FavouritesListPresenterImpl(@NonNull FavouritesListInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}