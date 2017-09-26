package com.astro.guide.utils.cache;

import android.util.Log;

import com.astro.guide.BuildConfig;
import com.astro.guide.model.AppUser;
import com.astro.guide.utils.PreferencesUtils;
import com.google.gson.Gson;

import java.util.Calendar;

import javax.inject.Inject;

public class AppCacheManager {

    private static final String TAG = AppCacheManager.class.getSimpleName();
    private static final long CACHE_DEFALUT_TIME_MILLIS = 60 * 1000 * BuildConfig.CACHE_MINUTES;

    @Inject
    protected PreferencesUtils mPreferencesUtils;

    @Inject
    public CacheManager mCacheManager;

    @Inject
    public AppCacheManager(PreferencesUtils preferencesUtils) {

        mPreferencesUtils = preferencesUtils;
    }

    public void saveForTimed(String data, String fileName) {
        mPreferencesUtils.putData(fileName, String.valueOf(Calendar.getInstance().getTimeInMillis()));
        save(data, fileName);
    }

    public void save(String data, String fileName) {
        try {
            mCacheManager.write(data, fileName);
        } catch (CacheTransactionException e) {
            e.printStackTrace();
        }
    }

    public String fetchTimed(String fileName) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long savedTime = Long.parseLong(mPreferencesUtils.getString(fileName, "0"));

        if (currentTime > (savedTime + CACHE_DEFALUT_TIME_MILLIS)) {
            return null;
        }

        return fetch(fileName);
    }

    public String fetch(String fileName) {
        String data = null;
        try {
            data = mCacheManager.readString(fileName);
            Log.i(TAG, "fetch=>" + data);
        } catch (CacheTransactionException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean clear(String fileName) {
        return mCacheManager.deleteFile(fileName);
    }

    public boolean clearCache() {
        return mCacheManager.deleteCacheDirContent();
    }

    public boolean hasCache() {
        return mCacheManager.hasCacheContent();
    }

    public AppUser getAppUser() {
        String settings = mPreferencesUtils.getString(CacheTag.APP_USER.name(), null);
        if (settings == null || settings.length() == 0) {
            return null;
        }
        return getAppUser(settings);
    }

    public AppUser getAppUser(String appUserJson) {
        return new Gson().fromJson(appUserJson, AppUser.class);
    }

    public void setAppUser(AppUser appUser) {
        String json = new Gson().toJson(appUser);
        mPreferencesUtils.putData(CacheTag.APP_USER.name(), json);
    }
}
