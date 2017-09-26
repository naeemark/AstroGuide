package com.astro.guide.features.onair.interactor.impl;

import android.content.Context;

import com.astro.guide.R;
import com.astro.guide.app.interactor.impl.BaseInteractorImpl;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.onair.interactor.OnAirInteractor;
import com.astro.guide.features.onair.presenter.OnAirPresenter;
import com.astro.guide.model.AppUser;
import com.astro.guide.model.Channel;
import com.astro.guide.model.response.channel.ChannelsResponse;
import com.astro.guide.model.response.event.EventsResponse;
import com.astro.guide.utils.DateTimeUtils;
import com.astro.guide.utils.SortUtils;
import com.astro.guide.utils.cache.AppCacheManager;
import com.astro.guide.utils.cache.CacheTag;
import com.astro.guide.utils.parser.ChannelParser;
import com.astro.guide.webapi.EventsApiService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import timber.log.Timber;

import static com.astro.guide.utils.SortUtils.sortList;

public final class OnAirInteractorImpl extends BaseInteractorImpl implements OnAirInteractor {

    private final Context mContext;
    private final EventsApiService mApiService;
    private final AppCacheManager mAppCacheManager;
    private final ChannelParser mChannelParser;
    private final AppUser mAppUser;

    @Inject
    public OnAirInteractorImpl(Context context, EventsApiService apiService, AppCacheManager appCacheManager, ChannelParser channelParser, AppUser appUser) {
        mContext = context;
        mApiService = apiService;
        mAppCacheManager = appCacheManager;
        mChannelParser = channelParser;
        mAppUser = appUser;
    }

    @Override
    public AppUser getAppUser() {
        return mAppUser;
    }

    @Override
    public void fetchData(OnFetchDataListener listener) {
        String cachedData = mAppCacheManager.fetchTimed(CacheTag.EVENTS.name());
        if (cachedData != null && !cachedData.isEmpty()) {
            EventsResponse response = new Gson().fromJson(cachedData, EventsResponse.class);
            Map<Integer, Channel> channelsMap = getChannelsMap();
            mChannelParser.mapEventsToChannels(response, channelsMap);
            listener.onDataResponse(new ArrayList<Channel>(channelsMap.values()));
            listener.onComplete();
        } else {
            fetchDataFromApi(listener);
        }
    }

    private void fetchDataFromApi(final OnFetchDataListener listener) {

        listener.onStart();
        final Map<Integer, Channel> hashMap = getChannelsMap();
        List<Integer> channelIds = new ArrayList<>(hashMap.keySet());
        Timber.i("SizeChannelIds: "+channelIds.size());

        Observable<EventsResponse> observable = mApiService.getEventsList(DateTimeUtils.getTimeRequestParams()[0], DateTimeUtils.getTimeRequestParams()[1], channelIds.toString());

        subscribe(observable, new Observer<EventsResponse>() {

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
            public void onNext(EventsResponse response) {
                mAppCacheManager.saveForTimed(new Gson().toJson(response), CacheTag.EVENTS.name());
                mChannelParser.mapEventsToChannels(response, hashMap);
                listener.onDataResponse(new ArrayList<Channel>(hashMap.values()));
            }
        });
    }

    @Override
    public void sortChannelsList(List<Channel> channelList, OnAirPresenter presenter) {
        presenter.onListSorted(sortList(channelList, AppConstants.SortOrder.values()[mAppUser.getSortOrder()]));
    }

    @Override
    public void sortChannelsList(ArrayList<Channel> channelList, AppConstants.SortOrder sortOrder, OnAirPresenter presenter) {
        Timber.e("AppUser:" + mAppUser.toString());
        if (sortOrder.ordinal() == mAppUser.getSortOrder()) {
            return;
        }
        mAppUser.setSortOrder(sortOrder.ordinal());
        updateCache();
        sortChannelsList(channelList, presenter);
    }

    private void updateCache() {
        Timber.e(mAppUser.toString());
        mAppCacheManager.setAppUser(mAppUser);
    }

    private Map<Integer, Channel> getChannelsMap() {
        String cachedData = mAppCacheManager.fetch(CacheTag.CHANNELS.name());
        Map<Integer, Channel> map = new HashMap<>();
        if (cachedData != null && !cachedData.isEmpty()) {
            ChannelsResponse response = new Gson().fromJson(cachedData, ChannelsResponse.class);
            List<Channel> channels = mChannelParser.parseChannels(response);
            channels = SortUtils.sortList(channels, AppConstants.SortOrder.SORT_BY_NUMBER);
            for (Channel channel : channels) {
                map.put(channel.getId(), channel);
            }
        }
        return map;
    }

    @Override
    public String getEmptyListPromptText() {
        return mContext.getString(R.string.lbl_no_list_items);
    }

    @Override
    public void clearCache() {
        boolean clear = mAppCacheManager.clear(CacheTag.EVENTS.name());
        Timber.e("clearCache: " + clear);
    }
}