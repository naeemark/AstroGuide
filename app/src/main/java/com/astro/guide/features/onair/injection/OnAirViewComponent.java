package com.astro.guide.features.onair.injection;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.features.onair.view.impl.OnAirActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = OnAirViewModule.class)
public interface OnAirViewComponent {
    void inject(OnAirActivity activity);
}