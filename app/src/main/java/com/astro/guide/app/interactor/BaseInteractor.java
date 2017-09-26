package com.astro.guide.app.interactor;

import com.astro.guide.model.Channel;

import java.util.List;

import rx.Observable;
import rx.Observer;

public interface BaseInteractor {
    <T> void subscribe(Observable<T> observable, Observer<T> observer);

    void unsubscribe();

    void cancelOnGoingHttpRequest();

    interface OnFetchDataListener {

        void onStart();

        void onDataResponse(List<Channel> channelList);

        void onFetchedFavouritesData(List<Channel> channelList);

        void onListSorted(List<Channel> channelList);

        void onFailure(String message);

        void onComplete();
    }
}
