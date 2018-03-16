package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class SearchTweetsEntity {

    @SerializedName("statuses")
    StatusesEntity getStatusesEntity;

    @SerializedName("search_metadata")
    SearchMetadataEntity getSearchMetadaEntity;
}
