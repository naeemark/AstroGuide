package com.astro.guide.features.splash;

import android.support.annotation.UiThread;

import com.astro.guide.app.view.BaseView;

@UiThread
public interface SplashView extends BaseView {

    void showLoading();

    void hideLoading();

    void finishActivity();

    void launchNextActivity();

    void showNetworkError();

}