package com.astro.guide.features.favourite.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.astro.guide.R;
import com.astro.guide.app.injection.AppComponent;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.app.view.impl.BaseActivity;
import com.astro.guide.features.favourite.injection.DaggerFavouritesListViewComponent;
import com.astro.guide.features.favourite.injection.FavouritesListViewModule;
import com.astro.guide.features.favourite.presenter.FavouritesListPresenter;
import com.astro.guide.features.favourite.view.FavouritesListView;

import javax.inject.Inject;

import timber.log.Timber;

public final class FavouritesListActivity extends BaseActivity<FavouritesListPresenter, FavouritesListView> implements FavouritesListView {
    @Inject
    PresenterFactory<FavouritesListPresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerFavouritesListViewComponent.builder()
                .appComponent(parentComponent)
                .favouritesListViewModule(new FavouritesListViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_favourites_list;
    }

    @NonNull
    @Override
    protected PresenterFactory<FavouritesListPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        showBackArrow();
        Timber.i("onViewReady()");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
