package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class MediaEntity {

    @SerializedName("media_url_https")
    private String mediaUrlHttps;

    public String getMediaUrlHttps() {
        return mediaUrlHttps;
    }

    public void setMediaUrlHttps(String mediaUrlHttps) {
        this.mediaUrlHttps = mediaUrlHttps;
    }
}
