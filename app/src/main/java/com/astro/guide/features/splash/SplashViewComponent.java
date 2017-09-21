package com.astro.guide.features.splash;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.injection.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashViewModule.class)
public interface SplashViewComponent {
    void inject(SplashActivity activity);
}