package com.astro.guide.features.splash;

import com.astro.guide.app.interactor.BaseInteractor;

public interface SplashInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    String getNoNetworkErrorText();

}