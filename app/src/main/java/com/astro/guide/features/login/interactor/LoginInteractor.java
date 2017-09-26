package com.astro.guide.features.login.interactor;

import com.astro.guide.app.interactor.BaseInteractor;
import com.astro.guide.model.AppUser;

public interface LoginInteractor extends BaseInteractor {

    void logout(OnLogoutListener listener);

    AppUser getAppUser();

    interface OnLogoutListener {

    }
}