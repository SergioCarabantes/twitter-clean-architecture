package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class SearchMetadataEntity {

    @SerializedName("max_id")
    private String maxId;

    @SerializedName("next_results")
    private String nextResults;

    @SerializedName("count")
    private int count;

    public String getNextResults() {
        return nextResults;
    }

    public void setNextResults(String nextResults) {
        this.nextResults = nextResults;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMaxId() {
        return maxId;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }
}
