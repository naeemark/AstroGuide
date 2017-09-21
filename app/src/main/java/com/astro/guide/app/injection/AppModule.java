package com.astro.guide.app.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.astro.guide.app.AstroGuideApp;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final AstroGuideApp mApp;

    public AppModule(@NonNull AstroGuideApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public AstroGuideApp provideApp() {
        return mApp;
    }
}
