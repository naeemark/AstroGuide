package com.astro.guide.features.login.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.astro.guide.app.injection.ActivityScope;
import com.astro.guide.app.presenter.loader.PresenterFactory;
import com.astro.guide.features.login.interactor.LoginInteractor;
import com.astro.guide.features.login.interactor.impl.LoginInteractorImpl;
import com.astro.guide.features.login.presenter.LoginPresenter;
import com.astro.guide.features.login.presenter.impl.LoginPresenterImpl;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;
import com.astro.guide.webapi.AppUserApiService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.astro.guide.BuildConfig.BASE_URL_AWS;

@Module
public final class LoginViewModule {
    @Provides
    public LoginInteractor provideInteractor(Context context, AppUser appUser, AppCacheManager appCacheManager, AppUserApiService apiService) {
        return new LoginInteractorImpl(context, appUser, appCacheManager, apiService);
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
    
    @ActivityScope
    @Provides
    AppUserApiService provideUserSettingsApiService(OkHttpClient client, GsonConverterFactory converterFactory, RxJavaCallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_AWS)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build().create(AppUserApiService.class);
    }
}
