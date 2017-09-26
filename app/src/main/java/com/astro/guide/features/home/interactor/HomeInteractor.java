package com.astro.guide.features.home.interactor;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;

import java.util.ArrayList;
import java.util.List;

public interface HomeInteractor extends BaseInteractor {

    AppUser getAppUser();

    boolean isNetworkConnected();

    void fetchData(OnFetchDataListener listener);

    void fetchFavouritesData(OnFetchDataListener listener);

    void clearCache();

    void sortChannelsList(List<Channel> channelList, HomePresenter presenter);

    void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder, HomePresenter presenter);

    void updateCache();

    void setHideFavouriteButton(boolean hidden);

    String getEmptyListPromptText();
}