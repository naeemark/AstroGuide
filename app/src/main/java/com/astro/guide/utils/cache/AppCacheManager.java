package com.astro.guide.utils.cache;

import android.util.Log;

import com.astro.guide.BuildConfig;
import com.astro.guide.utils.PreferencesUtils;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppCacheManager {

    private static final String TAG = AppCacheManager.class.getSimpleName();
    private static final long CACHE_DEFALUT_TIME_MILLIS = 60 * 1000 * BuildConfig.CACHE_MINUTES;

    @Inject
    protected PreferencesUtils mPreferencesUtils;

    @Inject
    public CacheManager mCacheManager;

    @Inject
    public AppCacheManager() {

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
}
