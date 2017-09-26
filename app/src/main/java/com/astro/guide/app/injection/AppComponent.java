package com.astro.guide.app.injection;

import android.content.Context;

import com.astro.guide.app.AstroGuideApp;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.PreferencesUtils;
import com.astro.guide.utils.parser.ChannelParser;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {

    Context getAppContext();

    AstroGuideApp getApp();

    PreferencesUtils exposePreferencesUtils();

    OkHttpClient exposeOkHttp();

    GsonConverterFactory exposeConverterFactory();

    RxJavaCallAdapterFactory exposeAdapterFactory();

    Retrofit exposeRetrofit();

    ChannelParser exposeParser();

    AppUser provideUserSettings();
}