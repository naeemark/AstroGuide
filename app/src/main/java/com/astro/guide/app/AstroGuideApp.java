package com.astro.guide.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.astro.guide.BuildConfig;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.injection.AppModule;
import com.astro.guide.app.injection.DaggerAppComponent;
import com.astro.guide.app.injection.NetworkApiModule;

import timber.log.Timber;

import static com.astro.guide.BuildConfig.BASE_URL;

public final class AstroGuideApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Shows logs only in Debug Build type
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkApiModule(new NetworkApiModule(BASE_URL))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}