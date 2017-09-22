package com.astro.guide.features.home.injection;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.features.home.view.impl.HomeActivity;
import com.astro.guide.features.home.view.impl._HomeActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = HomeViewModule.class)
public interface HomeViewComponent {
    void inject(HomeActivity activity);
    void inject(_HomeActivity activity);
}