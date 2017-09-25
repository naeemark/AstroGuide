package com.astro.guide.features.onair.presenter.impl;

import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.impl.BasePresenterImpl;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.onair.interactor.OnAirInteractor;
import com.astro.guide.features.onair.presenter.OnAirPresenter;
import com.astro.guide.features.onair.view.OnAirView;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class OnAirPresenterImpl extends BasePresenterImpl<OnAirView> implements OnAirPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final OnAirInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public OnAirPresenterImpl(@NonNull OnAirInteractor interactor) {
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
        fetchData();
    }

    @Override
    public void onStop() {
        mInteractor.cancelOnGoingHttpRequest();
        super.onStop();
    }

    private void fetchData() {
        mInteractor.fetchData(this);
    }

    @Override
    public void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder) {
        mInteractor.sortChannelsList(channelList, sortOrder, this);
    }

    @Override
    public void onDataResponse(List<Channel> channelList) {
        Timber.i(String.valueOf(channelList.size()));
        mInteractor.sortChannelsList(channelList, this);
    }

    @Override
    public void onFetchedFavouritesData(List<Channel> channelList) {}


    @Override
    public void onListSorted(List<Channel> channelList) {
        assert mView != null;
        if (channelList.isEmpty()){
            mView.showPrompt(mInteractor.getEmptyListPromptText());
        }

        mView.loadList(channelList);
    }
}