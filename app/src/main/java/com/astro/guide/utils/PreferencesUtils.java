/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.astro.guide.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.astro.guide.constants.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class PreferencesUtils {

    public enum PrefKeys {
        API_TOKEN
    }

    private final SharedPreferences mPref;

    @Inject
    public PreferencesUtils(Context context) {
        mPref = context.getSharedPreferences(AppConstants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putData(String key, String data) {
        mPref.edit().putString(key, data).apply();
    }

    public String getData(String key) {
        return mPref.getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return mPref.getString(key, defaultValue);
    }

    public boolean isLoggedIn() {
        String apiToken = this.getData(PrefKeys.API_TOKEN.name());

        if (apiToken != null && !apiToken.isEmpty()) {
            return true;
        }
        return false;
    }
}
