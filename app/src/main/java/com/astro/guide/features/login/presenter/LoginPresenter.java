package com.astro.guide.features.login.presenter;

import com.astro.guide.app.presenter.BasePresenter;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.features.login.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView>, LoginInteractor.OnLogoutListener {

    void logout();
}