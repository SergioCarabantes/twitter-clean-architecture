package com.sergio.twitter.data.local;

import android.content.SharedPreferences;
import com.sergio.twitter.domain.local.PreferenceRepository;

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
