package com.astro.guide.features.login.interactor.impl;

import android.content.Context;

import com.astro.guide.R;
import com.astro.guide.app.interactor.impl.BaseInteractorImpl;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;
import com.astro.guide.webapi.AppUserApiService;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import timber.log.Timber;

public final class LoginInteractorImpl extends BaseInteractorImpl implements LoginInteractor {


    private final Context mContext;
    private final AppUser mAppUser;
    private final AppCacheManager mCacheManager;
    private final AppUserApiService mApiService;


    @Inject
    public LoginInteractorImpl(Context context, AppUser appUser, AppCacheManager cacheManager, AppUserApiService apiService) {

        mContext = context;
        mAppUser = appUser;
        mCacheManager = cacheManager;
        mApiService = apiService;
    }

    @Override
    public AppUser getAppUser() {
        return mAppUser;
    }


    @Override
    public void fetchAppUserSettings(GoogleSignInAccount acct, final OnSyncSettingsListener listener) {

        listener.onStart();

        Observable<AppUser> observable = mApiService.get(acct.getEmail());

        subscribe(observable, new Observer<AppUser>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e.getMessage());
                listener.onComplete();
            }

            @Override
            public void onNext(AppUser appUser) {
                mAppUser.updateData(appUser.getName(), appUser.getEmail(), appUser.getSortOrder());
                mAppUser.setFavouritesIds(appUser.getFavouritesIds());
                mAppUser.setLoggedIn(true);
                updateCache();
                listener.onFetchSettings(getAppUser());
            }
        });
    }

    @Override
    public void logout(final OnSyncSettingsListener listener) {
        Timber.i("updateUi(OnLogoutListener listener)");
        listener.onStart();
        Call<String> call = mApiService.post(mAppUser.getEmail(), new Gson().toJson(mAppUser));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                clearAppUser();
                listener.onUploadSettings(getAppUser());
                listener.onComplete();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFailure(t.getMessage());
                listener.onComplete();
            }
        });
    }

    private void clearAppUser() {
        mAppUser.updateData(mContext.getString(R.string.guest_user_name), mContext.getString(R.string.guest_user_email), AppConstants.SortOrder.SORT_BY_NAME.ordinal());
        mAppUser.setLoggedIn(false);
        mAppUser.getFavouritesIds().clear();
        updateCache();
    }

    @Override
    public void updateCache() {
        Timber.e(mAppUser.toString());
        mCacheManager.setAppUser(mAppUser);
    }

}