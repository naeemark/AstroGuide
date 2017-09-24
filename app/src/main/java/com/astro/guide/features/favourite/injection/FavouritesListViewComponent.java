package com.astro.guide.features.favourite.injection;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.features.favourite.view.impl.FavouritesListActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = FavouritesListViewModule.class)
public interface FavouritesListViewComponent {
    void inject(FavouritesListActivity activity);
}