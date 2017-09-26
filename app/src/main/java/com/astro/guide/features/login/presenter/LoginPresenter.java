package com.astro.guide.features.login.presenter;

import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.features.login.view.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public interface LoginPresenter extends BasePresenter<LoginView>, LoginInteractor.OnSyncSettingsListener {

    void logout();

    void handleSignInResult(GoogleSignInResult gsr);
}