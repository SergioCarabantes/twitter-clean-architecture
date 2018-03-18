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

package com.sergio.twitter.data.tweets.model;

import com.google.gson.annotations.SerializedName;

public class StatusesEntity {

    @SerializedName("entities")
    private EntitiesEntity entity;

    @SerializedName("extended_entities")
    private EntitiesEntity extendedEntities;

    @SerializedName("user")
    private UserEntity userEntity;

    public EntitiesEntity getEntity() {
        return entity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public EntitiesEntity getExtendedEntities() {
        return extendedEntities;
    }
}
