package com.astro.guide.features.home.presenter.impl;

import android.support.annotation.NonNull;

import com.astro.guide.R;
import com.astro.guide.app.presenter.impl.BasePresenterImpl;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.home.interactor.HomeInteractor;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.features.home.view.HomeView;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

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
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated) {
            assert mView != null;
            mView.setDrawerHeaderData(mInteractor.getAppUser());
            mView.setSortButtonChecked(mInteractor.getAppUser().getSortOrder());
            fetchData();
        }
    }

    @Override
    public void onStop() {
        // Stops request in progress before exiting the presenter
        mInteractor.cancelOnGoingHttpRequest();
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();
    }

    @Override
    public void checkNetwork() {
        if (!mInteractor.isNetworkConnected()) {
            assert mView != null;
            mView.showNetworkError();
        }
    }

    @Override
    public void fetchData() {
        mInteractor.fetchData(this);
    }

    @Override
    public void onFabClicked() {
        assert mView != null;
        mView.showToast("onFabClicked()");
        mView.launchFavouritesListActivity();
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
            mView.showInfo();
        }
    }

    @Override
    public void onRefreshClicked() {
        mInteractor.clearChannelsCache();
        fetchData();
    }

    @Override
    public void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder) {
        mInteractor.sortChannelsList(channelList, sortOrder, this);
    }

    @Override
    public void updateCache() {
        mInteractor.updateCache();
    }

    @Override
    public void onStart() {
        assert mView != null;
        mView.showLoading();
    }

    @Override
    public void onDataResponse(List<Channel> channelList) {
        Timber.i(String.valueOf(channelList.size()));
        mInteractor.sortChannelsList(channelList, this);
    }

    @Override
    public void onListSorted(List<Channel> channelList) {
        assert mView != null;
        mView.loadList(channelList);
    }

    @Override
    public void onFailure(String message) {
        Timber.d(message);
        assert mView != null;
        mView.hideLoading();
        mView.showErrorLoading();
    }

    @Override
    public void onComplete() {
        assert mView != null;
        mView.hideLoading();
    }
}