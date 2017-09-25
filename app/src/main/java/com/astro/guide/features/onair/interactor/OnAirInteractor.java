package com.astro.guide.features.onair.interactor;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.onair.presenter.OnAirPresenter;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

public interface OnAirInteractor extends BaseInteractor {

    AppUser getAppUser();

    void fetchData(OnFetchDataListener listener);

    void sortChannelsList(List<Channel> channelList, OnAirPresenter presenter);

    void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder, OnAirPresenter presenter);

    String getEmptyListPromptText();
}