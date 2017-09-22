package com.astro.guide.features.home.interactor;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.model.Channel;

import java.util.List;

public interface HomeInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    void fetchData(OnFetchDataListener listener);

    void cancelOnGoingHttpRequest();

    void clearChannelsCache();

    interface  OnFetchDataListener {

        void onDataResponse(List<Channel> channelList);

        void onFailure(String message);

        void onComplete();

    }

}