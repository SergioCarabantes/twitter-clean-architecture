package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class StatusesEntity {

    @SerializedName("entities")
    private EntitiesEntity entity;

    public EntitiesEntity getEntity() {
        return entity;
    }

    public void setEntity(EntitiesEntity entity) {
        this.entity = entity;
    }
}
