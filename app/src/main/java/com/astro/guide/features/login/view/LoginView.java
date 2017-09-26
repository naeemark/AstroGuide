package com.astro.guide.features.login.view;

import android.support.annotation.UiThread;
import android.view.View;

import com.astro.guide.app.view.BaseView;
import com.astro.guide.model.AppUser;
import com.google.android.gms.common.api.GoogleApiClient;

@UiThread
public interface LoginView extends BaseView, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    void updateUi(AppUser appUser);

    void logout();
}