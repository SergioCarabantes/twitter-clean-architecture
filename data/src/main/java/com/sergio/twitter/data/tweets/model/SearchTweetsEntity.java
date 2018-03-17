package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTweetsEntity {

    @SerializedName("statuses")
    private List<StatusesEntity> getStatusesEntity;

    @SerializedName("search_metadata")
    private SearchMetadataEntity searchMetadataEntity;

    public List<StatusesEntity> getGetStatusesEntity() {
        return getStatusesEntity;
    }

    public void setGetStatusesEntity(List<StatusesEntity> getStatusesEntity) {
        this.getStatusesEntity = getStatusesEntity;
    }

    public SearchMetadataEntity getSearchMetadataEntity() {
        return searchMetadataEntity;
    }

    public void setSearchMetadataEntity(SearchMetadataEntity searchMetadataEntity) {
        this.searchMetadataEntity = searchMetadataEntity;
    }
}
