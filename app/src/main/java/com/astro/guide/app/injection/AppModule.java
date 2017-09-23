package com.astro.guide.app.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.astro.guide.R;
import com.astro.guide.app.AstroGuideApp;
import com.astro.guide.constants.AppConstants;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.cache.AppCacheManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final AstroGuideApp mApp;

    public AppModule(@NonNull AstroGuideApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public AstroGuideApp provideApp() {
        return mApp;
    }

    @Singleton
    @Provides
    public AppUser provideAppUser(Context context, AppCacheManager cacheManager){
        AppUser cachedAppUser = cacheManager.getAppUser();
        if (cachedAppUser == null) {
            cachedAppUser = new AppUser(context.getString(R.string.guest_user_name), context.getString(R.string.guest_user_email), AppConstants.SortOrder.SORT_BY_NAME.ordinal());
            cacheManager.setAppUser(cachedAppUser);
        }
        return cachedAppUser;
    }
}
