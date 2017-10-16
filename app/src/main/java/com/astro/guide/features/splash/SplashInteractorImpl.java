package com.astro.guide.features.splash;

import android.content.Context;

import com.astro.guide.R;
import com.astro.guide.app.interactor.impl.BaseInteractorImpl;
import com.astro.guide.utils.NetworkUtils;
import com.astro.guide.utils.PreferencesUtils;

import javax.inject.Inject;

public final class SplashInteractorImpl extends BaseInteractorImpl implements SplashInteractor {

    private final Context mContext;

    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public SplashInteractorImpl(Context context, PreferencesUtils preferencesUtils) {
        this.mContext = context;
        this.mPreferencesUtils = preferencesUtils;
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public String getNoNetworkErrorText() {
        return mContext.getString(R.string.error_no_network);
    }

    @Override
    public boolean isSplashDone() {
        return mPreferencesUtils.getBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name());
    }

    @Override
    public void setSpalshDone() {
        mPreferencesUtils.putBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name(), true);
    }

}