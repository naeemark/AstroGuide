package com.astro.guide.features.home.presenter.impl;

import android.support.annotation.NonNull;

import com.astro.guide.R;
import com.astro.guide.app.presenter.impl.BasePresenterImpl;
import com.astro.guide.features.home.interactor.HomeInteractor;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.features.home.view.HomeView;

import javax.inject.Inject;

public final class HomePresenterImpl extends BasePresenterImpl<HomeView> implements HomePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomeInteractor mInteractor;

    @Inject
    public HomePresenterImpl(@NonNull HomeInteractor interactor) {
        mInteractor = interactor;
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onFabClicked() {
        assert mView != null;
        mView.showToast("onFabClicked()");
    }

    @Override
    public void onNavigationItemSelected(int itemId) {

        assert mView != null;
        if (itemId == R.id.nav_channels) {
            mView.showToast("R.id.nav_channels");
        } else if (itemId == R.id.nav_epg) {
            mView.showToast("R.id.nav_epg");
        } else if (itemId == R.id.nav_login) {

        } else if (itemId == R.id.nav_logout) {

        } else if (itemId == R.id.nav_info) {
            mView.showToast("R.id.nav_info");
        }
    }
}