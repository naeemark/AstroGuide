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

        Timber.e("onStart:" + mInteractor.getAppUser().toString());
        if (viewCreated) {
            // nothing to do with firstStart
        }
        assert mView != null;
        mView.setSortButtonChecked(mInteractor.getAppUser().getSortOrder());
        if (mView.isForFavourites()) {
            mInteractor.setHideFavouriteButton(true);
            fetchFavouritesData();
        } else {
            mView.setDrawerHeaderData(mInteractor.getAppUser());
            mInteractor.setHideFavouriteButton(false);
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

    private void fetchFavouritesData() {
        mInteractor.fetchFavouritesData(this);
    }

    @Override
    public void onFabClicked() {
        assert mView != null;
        mView.launchFavouritesListActivity();
    }

    @Override
    public void onNavigationItemSelected(int itemId) {

        assert mView != null;
        if (itemId == R.id.nav_channels) {
            Timber.e("R.id.nav_channels");
        } else if (itemId == R.id.nav_on_air) {
            mView.launchOnAirActivity();
        } else if (itemId == R.id.nav_login) {
            mView.launchLoginActivity();
        } else if (itemId == R.id.nav_logout) {
            mView.logout();
        } else if (itemId == R.id.nav_info) {
            mView.showInfo();
        }
    }

    @Override
    public void onRefreshClicked() {
        mInteractor.clearCache();
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
    public void onDataResponse(List<Channel> channelList) {
        Timber.i(String.valueOf(channelList.size()));
        mInteractor.sortChannelsList(channelList, this);
    }

    @Override
    public void onFetchedFavouritesData(List<Channel> channelList) {
        mInteractor.sortChannelsList(channelList, this);
    }

    @Override
    public void onListSorted(List<Channel> channelList) {
        assert mView != null;
        if (channelList.isEmpty()){
            mView.showPrompt(mInteractor.getEmptyListPromptText());
        }
        mView.loadList(channelList);
    }
}