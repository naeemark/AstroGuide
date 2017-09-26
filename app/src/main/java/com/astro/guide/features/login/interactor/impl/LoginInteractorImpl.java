package com.astro.guide.features.login.interactor.impl;

import android.content.Context;

import com.astro.guide.R;
import com.astro.guide.app.interactor.impl.BaseInteractorImpl;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
    public void updateAppUser(GoogleSignInAccount account) {
        mAppUser.setName(account.getDisplayName());
        mAppUser.setEmail(account.getEmail());
        mAppUser.setLoggedIn(true);
    }

    @Override
    public void logout(OnSyncSettingsListener listener) {
        Timber.i("updateUi(OnLogoutListener listener)");
        mAppUser.setName(mContext.getString(R.string.guest_user_name));
        mAppUser.setEmail(mContext.getString(R.string.guest_user_email));
        mAppUser.setSortOrder( AppConstants.SortOrder.SORT_BY_NAME.ordinal());
        mAppUser.setLoggedIn(false);
        mAppUser.getFavouritesIds().clear();
        updateCache();
        listener.onUploadSettings(getAppUser());
        listener.onComplete();
    }

    @Override
    public void fetchAppUserSettings(OnSyncSettingsListener listener) {
        // set app user by HTTP
        updateCache();
        listener.onFetchSettings(getAppUser());
        listener.onComplete();
    }

    @Override
    public void updateCache() {
        Timber.e(mAppUser.toString());
        mCacheManager.setAppUser(mAppUser);
    }

}