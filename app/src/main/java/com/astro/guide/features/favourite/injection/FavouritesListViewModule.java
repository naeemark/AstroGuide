package com.astro.guide.features.favourite.injection;

import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.features.favourite.interactor.FavouritesListInteractor;
import com.astro.guide.features.favourite.interactor.impl.FavouritesListInteractorImpl;
import com.astro.guide.features.favourite.presenter.FavouritesListPresenter;
import com.astro.guide.features.favourite.presenter.impl.FavouritesListPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class FavouritesListViewModule {
    @Provides
    public FavouritesListInteractor provideInteractor() {
        return new FavouritesListInteractorImpl();
    }

    @Provides
    public PresenterFactory<FavouritesListPresenter> providePresenterFactory(@NonNull final FavouritesListInteractor interactor) {
        return new PresenterFactory<FavouritesListPresenter>() {
            @NonNull
            @Override
            public FavouritesListPresenter create() {
                return new FavouritesListPresenterImpl(interactor);
            }
        };
    }
}
