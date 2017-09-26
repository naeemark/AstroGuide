package com.astro.guide.app.interactor.impl;

import com.astro.guide.app.interactor.BaseInteractor;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 * @since 22/9/2017
 */

public class BaseInteractorImpl implements BaseInteractor {

    public Subscription mSubscription = Subscriptions.empty();

    @Override
    public <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        mSubscription =  observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            Timber.i("mSubscription.unsubscribe()");
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void cancelOnGoingHttpRequest() {
        Timber.i("cancelOnGoingHttpRequest()");
        unsubscribe();
    }
}
