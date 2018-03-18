/*
 * Copyright (C) 2018 Sergio Carabantes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergio.twitter.data.preferences;

import android.content.SharedPreferences;
import com.sergio.twitter.domain.preferences.PreferenceRepository;

import javax.inject.Inject;

public class PreferenceRepositoryImpl implements PreferenceRepository {

    private final SharedPreferences sharedPreferences;
    private final String ACCESS_TOKEN_KEY = "access_token_key";

    @Inject
    public PreferenceRepositoryImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        setString(ACCESS_TOKEN_KEY, accessToken);
    }

    @Override
    public String getAccessToken() {
        return getString(ACCESS_TOKEN_KEY, null);
    }

    private String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    private void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }
}
