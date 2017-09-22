package com.astro.guide.features.home.injection;

import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.features.home.interactor.HomeInteractor;
import com.astro.guide.features.home.interactor.impl.HomeInteractorImpl;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.features.home.presenter.impl.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor() {
        return new HomeInteractorImpl();
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return new PresenterFactory<HomePresenter>() {
            @NonNull
            @Override
            public HomePresenter create() {
                return new HomePresenterImpl(interactor);
            }
        };
    }
}
