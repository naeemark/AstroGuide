package com.astro.guide.features.login.interactor.impl;

import android.content.Context;

import com.astro.guide.app.interactor.impl.BaseInteractorImpl;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;

import javax.inject.Inject;

import timber.log.Timber;

public final class LoginInteractorImpl extends BaseInteractorImpl implements LoginInteractor {


    private final Context mContext;
    private final AppUser mAppUser;
    private final AppCacheManager mCacheManager;

    @Inject
    public LoginInteractorImpl(Context context, AppUser appUser,  AppCacheManager cacheManager) {

        mContext = context;
        mAppUser = appUser;
        mCacheManager = cacheManager;
    }

    @Override
    public AppUser getAppUser() {
        return mAppUser;
    }

    @Override
    public void logout(OnLogoutListener listener) {
        Timber.i("updateUi(OnLogoutListener listener)");
    }

}