package com.astro.guide.app.injection;

import android.content.Context;

import com.astro.guide.app.AstroGuideApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    AstroGuideApp getApp();
}