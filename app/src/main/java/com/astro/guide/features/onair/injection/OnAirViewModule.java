package com.astro.guide.features.onair.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.features.onair.interactor.OnAirInteractor;
import com.astro.guide.features.onair.interactor.impl.OnAirInteractorImpl;
import com.astro.guide.features.onair.presenter.OnAirPresenter;
import com.astro.guide.features.onair.presenter.impl.OnAirPresenterImpl;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;
import com.astro.guide.utils.parser.ChannelParser;
import com.astro.guide.webapi.EventsApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class OnAirViewModule {
    @Provides
    public OnAirInteractor provideInteractor(Context context, EventsApiService eventsApiService, AppCacheManager appCacheManager, ChannelParser channelParser, AppUser appUser) {
        return new OnAirInteractorImpl(context, eventsApiService, appCacheManager, channelParser, appUser);
    }

    @Provides
    public PresenterFactory<OnAirPresenter> providePresenterFactory(@NonNull final OnAirInteractor interactor) {
        return new PresenterFactory<OnAirPresenter>() {
            @NonNull
            @Override
            public OnAirPresenter create() {
                return new OnAirPresenterImpl(interactor);
            }
        };
    }

    @ActivityScope
    @Provides
    EventsApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(EventsApiService.class);
    }
}
