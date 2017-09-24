package com.astro.guide.features.splash;


import com.astro.guide.app.presenter.BasePresenter;

public interface SplashPresenter extends BasePresenter<SplashView> {

    void startLoading();

    void stopLoading();

    void doSplash();

    void checkNetwork();

    void launchNextActivity();
}