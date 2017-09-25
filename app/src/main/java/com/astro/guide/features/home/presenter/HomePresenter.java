package com.astro.guide.features.home.presenter;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.home.view.HomeView;
import com.astro.guide.model.Channel;

import java.util.ArrayList;

public interface HomePresenter extends BasePresenter<HomeView>, BaseInteractor.OnFetchDataListener {

    void onFabClicked();

    void checkNetwork();

    void fetchData();

    void onNavigationItemSelected(int itemId);

    void onRefreshClicked();

    void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder);

    void updateCache();
}