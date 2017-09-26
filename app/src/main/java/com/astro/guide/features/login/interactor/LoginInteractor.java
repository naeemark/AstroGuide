package com.astro.guide.features.login.interactor;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.model.AppUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginInteractor extends BaseInteractor {

    void logout(OnSyncSettingsListener listener);

    void fetchAppUserSettings(OnSyncSettingsListener listener);

    AppUser getAppUser();

    void updateAppUser(GoogleSignInAccount account);

    void updateCache();

    interface OnSyncSettingsListener {
        void onStart();
        void onFetchSettings(AppUser appUser);
        void onUploadSettings(AppUser appUser);
        void onFailure(String msg);
        void onComplete();
    }
}