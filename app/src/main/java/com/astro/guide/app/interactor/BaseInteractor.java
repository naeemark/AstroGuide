package com.astro.guide.app.interactor;

import rx.Observable;
import rx.Observer;

public interface BaseInteractor {
    <T> void subscribe(Observable<T> observable, Observer<T> observer);

    void unsubscribe();
}
