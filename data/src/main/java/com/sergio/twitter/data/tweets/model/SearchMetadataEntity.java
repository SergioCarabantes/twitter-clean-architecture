package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class SearchMetadataEntity {

    @SerializedName("count")
    private int getCount;

    public int getGetCount() {
        return getCount;
    }

    public void setGetCount(int getCount) {
        this.getCount = getCount;
    }
}
