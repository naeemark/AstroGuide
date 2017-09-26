package com.astro.guide.features.login.presenter.impl;

import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.impl.BasePresenterImpl;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.features.login.presenter.LoginPresenter;
import com.astro.guide.features.login.view.LoginView;
import com.astro.guide.model.AppUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import javax.inject.Inject;

import timber.log.Timber;

public final class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final LoginInteractor mInteractor;

    @Inject
    public LoginPresenterImpl(@NonNull LoginInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        assert mView != null;
        mView.updateUi(mInteractor.getAppUser());
    }

    @Override
    public void onStop() {
        mInteractor.cancelOnGoingHttpRequest();
        super.onStop();
    }

    @Override
    public void logout() {
        mInteractor.logout(this);
    }

    @Override
    public void handleSignInResult(GoogleSignInResult result) {
        Timber.d("handleSignInResult:" + result.getStatus().getStatusMessage());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mInteractor.fetchAppUserSettings(acct, this);
        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    @Override
    public void onFailure(String message) {
        Timber.d(message);
        assert mView != null;
        mView.showErrorWithMessage(message);
    }

    @Override
    public void onFetchSettings(AppUser appUser) {
        assert mView != null;
        mView.updateUi(appUser);
    }

    @Override
    public void onUploadSettings(AppUser appUser) {
        assert mView != null;
        mView.logout();
        mView.updateUi(appUser);
    }
}