package com.astro.guide.features.login.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.features.login.interactor.impl.LoginInteractorImpl;
import com.astro.guide.features.login.presenter.LoginPresenter;
import com.astro.guide.features.login.presenter.impl.LoginPresenterImpl;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;

import dagger.Module;
import dagger.Provides;

@Module
public final class LoginViewModule {
    @Provides
    public LoginInteractor provideInteractor(Context context, AppUser appUser, AppCacheManager appCacheManager) {
        return new LoginInteractorImpl(context, appUser, appCacheManager);
    }

    @Provides
    public PresenterFactory<LoginPresenter> providePresenterFactory(@NonNull final LoginInteractor interactor) {
        return new PresenterFactory<LoginPresenter>() {
            @NonNull
            @Override
            public LoginPresenter create() {
                return new LoginPresenterImpl(interactor);
            }
        };
    }
}
