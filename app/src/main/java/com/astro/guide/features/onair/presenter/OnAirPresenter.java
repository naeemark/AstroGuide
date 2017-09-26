package com.astro.guide.features.onair.presenter;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.onair.view.OnAirView;
import com.astro.guide.model.Channel;

import java.util.ArrayList;

public interface OnAirPresenter extends BasePresenter<OnAirView>, BaseInteractor.OnFetchDataListener {

    void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder);

    void onRefreshClicked();
}