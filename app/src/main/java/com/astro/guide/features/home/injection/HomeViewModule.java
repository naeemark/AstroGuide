package com.astro.guide.features.home.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.features.home.interactor.HomeInteractor;
import com.astro.guide.features.home.interactor.impl.HomeInteractorImpl;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.features.home.presenter.impl.HomePresenterImpl;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.PreferencesUtils;
import com.astro.guide.utils.cache.AppCacheManager;
import com.astro.guide.utils.parser.ChannelParser;
import com.astro.guide.webapi.ChannelsApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class HomeViewModule {

    @Provides
    public HomeInteractor provideInteractor(Context context, ChannelsApiService channelsApiService, PreferencesUtils preferencesUtils, ChannelParser parser, AppCacheManager cacheManager, AppUser userSettings) {
        return new HomeInteractorImpl(context, channelsApiService, preferencesUtils, parser, cacheManager, userSettings);
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

    @ActivityScope
    @Provides
    ChannelsApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ChannelsApiService.class);
    }

}
