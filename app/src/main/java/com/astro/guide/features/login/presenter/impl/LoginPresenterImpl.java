package com.astro.guide.features.login.presenter.impl;

import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.impl.BasePresenterImpl;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.features.login.presenter.LoginPresenter;
import com.astro.guide.features.login.view.LoginView;

import javax.inject.Inject;

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
}