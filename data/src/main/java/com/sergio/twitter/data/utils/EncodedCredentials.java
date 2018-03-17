package com.sergio.twitter.data.utils;

import android.util.Base64;
import com.sergio.twitter.data.BuildConfig;

import javax.inject.Inject;

public class EncodedCredentials {

    @Inject
    public EncodedCredentials() {

    }

    public String getCredentials() {
        return "Basic " + getBase64String(BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET);
    }

    private String getBase64String(String value) {
        return Base64.encodeToString(value.getBytes(), Base64.NO_WRAP);
    }

}
