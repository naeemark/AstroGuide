package com.astro.guide.app.presenter.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.app.view.BaseView;

import timber.log.Timber;

/**
 * Abstract presenter implementation that contains base implementation for other presenters.
 * Subclasses must call super for all {@link BasePresenter} method overriding.
 */
public abstract class BasePresenterImpl<V> implements BasePresenter<V> {
    /**
     * The view
     */
    @Nullable
    protected V mView;

    @Override
    public void onViewAttached(@NonNull V view) {
        mView = view;
    }


    @Override
    public void onStart(boolean viewCreated) {

    }

    @Override
    public void onStop() {

    }


    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onPresenterDestroyed() {

    }

    @Override
    public void onStart() {
        assert mView != null;
        ((BaseView)mView).showLoading();
    }

    @Override
    public void onFailure(String message) {
        Timber.d(message);
        assert mView != null;
        BaseView view = (BaseView) mView;
        view.hideLoading();
        view.showErrorLoading();
    }

    @Override
    public void onComplete() {
        assert mView != null;
        ((BaseView)mView).hideLoading();
    }
}
