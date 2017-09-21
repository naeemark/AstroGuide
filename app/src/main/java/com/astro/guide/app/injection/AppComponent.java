package com.astro.guide.app.injection;

import android.content.Context;

import com.astro.guide.app.AstroGuideApp;
import com.astro.guide.utils.PreferencesUtils;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {

    Context getAppContext();

    AstroGuideApp getApp();

    PreferencesUtils exposePreferencesUtils();

    Retrofit exposeRetrofit();
}