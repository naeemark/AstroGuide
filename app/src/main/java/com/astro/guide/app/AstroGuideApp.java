package com.astro.guide.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.astro.guide.BuildConfig;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.injection.AppModule;
import com.astro.guide.app.injection.DaggerAppComponent;

import timber.log.Timber;

public final class AstroGuideApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}