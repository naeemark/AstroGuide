package com.astro.guide.features.home.interactor.impl;

import android.content.Context;

import com.astro.guide.app.interactor.impl.BaseInteractorImpl;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.home.interactor.HomeInteractor;
import com.astro.guide.features.home.presenter.HomePresenter;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;
import com.astro.guide.model.response.channel.ChannelsResponse;
import com.astro.guide.utils.NetworkUtils;
import com.astro.guide.utils.PreferencesUtils;
import com.astro.guide.utils.SortUtils;
import com.astro.guide.utils.cache.AppCacheManager;
import com.astro.guide.utils.cache.CacheTag;
import com.astro.guide.utils.parser.ChannelParser;
import com.astro.guide.webapi.ChannelsApiService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import timber.log.Timber;

public final class HomeInteractorImpl extends BaseInteractorImpl implements HomeInteractor {

    private final Context mContext;
    private final ChannelsApiService mApiService;
    private final PreferencesUtils mPreferencesUtils;
    private final ChannelParser mChannelParser;
    private final AppCacheManager mCacheManager;
    private final AppUser mAppUser;

    @Inject
    public HomeInteractorImpl(Context context, ChannelsApiService channelsApiService, PreferencesUtils preferencesUtils, ChannelParser channelParser, AppCacheManager cacheManager, AppUser appUser) {
        mContext = context;
        mApiService = channelsApiService;
        mPreferencesUtils = preferencesUtils;
        mChannelParser = channelParser;
        mCacheManager = cacheManager;
        mAppUser = appUser;
    }

    @Override
    public AppUser getAppUser() {
        return mAppUser;
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public void fetchData(final OnFetchDataListener listener) {
        String cachedData = mCacheManager.fetch(CacheTag.CHANNELS.name());
        if (cachedData != null && !cachedData.isEmpty()) {
            ChannelsResponse response = new Gson().fromJson(cachedData, ChannelsResponse.class);
            listener.onDataResponse(mChannelParser.parseChannels(response));
            listener.onComplete();
        } else {
            fetchDataFromApi(listener);
        }
    }

    private void fetchDataFromApi(final OnFetchDataListener listener) {
        listener.onStart();

        Observable<ChannelsResponse> observable = mApiService.getChannels();

        subscribe(observable, new Observer<ChannelsResponse>() {

            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e.getMessage());
                listener.onComplete();
            }

            @Override
            public void onNext(ChannelsResponse response) {
                mCacheManager.save(new Gson().toJson(response), CacheTag.CHANNELS.name());
                List<Channel> channels = mChannelParser.parseChannels(response);
                listener.onDataResponse(channels);
            }
        });
    }

    @Override
    public void cancelOnGoingHttpRequest() {
        Timber.i("cancelOnGoingHttpRequest()");
        unsubscribe();
    }

    @Override
    public void clearChannelsCache() {
        boolean clear = mCacheManager.clear(CacheTag.CHANNELS.name());
        Timber.e("clearChannelsCache: "+ clear);
    }

    @Override
    public void sortChannelsList(List<Channel> channelList, HomePresenter presenter) {
        presenter.onListSorted(SortUtils.sortList(channelList, AppConstants.SortOrder.values()[mAppUser.getSortOrder()]));
    }

    @Override
    public void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder, HomePresenter presenter) {
        Timber.e("AppUser:"+ mAppUser.toString());
        if(sortOrder.ordinal() == mAppUser.getSortOrder()){
            return;
        }
        mAppUser.setSortOrder(sortOrder.ordinal());
        updateCache();
        sortChannelsList(channelList, presenter);
    }

    @Override
    public void updateCache() {
        Timber.e(mAppUser.toString());
        mCacheManager.setAppUser(mAppUser);
    }
}