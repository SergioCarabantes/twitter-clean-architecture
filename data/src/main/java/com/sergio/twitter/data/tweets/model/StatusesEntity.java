package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class StatusesEntity {

    @SerializedName("id_str")
    String getIdStr;

    @SerializedName("entities")
    EntitiesEntity getEntitiesEntity;
}
