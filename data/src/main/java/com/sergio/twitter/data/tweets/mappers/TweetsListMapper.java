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

package com.sergio.twitter.data.tweets.mappers;

import com.sergio.twitter.data.Mapper;
import com.sergio.twitter.data.tweets.model.MediaEntity;
import com.sergio.twitter.data.tweets.model.SearchTweetsEntity;
import com.sergio.twitter.data.tweets.model.StatusesEntity;
import com.sergio.twitter.domain.tweets.model.Entities;
import com.sergio.twitter.domain.tweets.model.Media;
import com.sergio.twitter.domain.tweets.model.SearchMetadata;
import com.sergio.twitter.domain.tweets.model.SearchTweets;
import com.sergio.twitter.domain.tweets.model.Statuses;
import com.sergio.twitter.domain.tweets.model.User;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TweetsListMapper implements Mapper<SearchTweetsEntity, SearchTweets> {

    @Inject
    public TweetsListMapper() {
    }

    @Override
    public SearchTweets map(SearchTweetsEntity searchTweetsEntity) {
        SearchTweets searchTweets = new SearchTweets();
        searchTweets.setStatuses(getStatuses(searchTweetsEntity.getStatusesEntityList()));
        SearchMetadata searchMetadata = new SearchMetadata();
        searchMetadata.setCount(searchTweetsEntity.getSearchMetadataEntity().getCount());
        searchMetadata.setNextResults(searchTweetsEntity.getSearchMetadataEntity().getNextResults());
        searchMetadata.setMaxId(searchTweetsEntity.getSearchMetadataEntity().getMaxId());
        searchTweets.setSearchMetadata(searchMetadata);
        return searchTweets;
    }

    private List<Statuses> getStatuses(List<StatusesEntity> statusesEntityList) {
        List<Statuses> statusesList = new ArrayList<>();
        for(StatusesEntity statusesEntity: statusesEntityList) {
            Entities entities = new Entities();

            List<MediaEntity> listMediaEntity = statusesEntity.getEntity().getMediaEntityList();
            if (listMediaEntity != null) {
                entities.setMediaList(getMedia(listMediaEntity));
            }

            Entities extendedEntities = new Entities();
            if (statusesEntity.getExtendedEntities() != null) {
                List<MediaEntity> listMediaExtendedEntity = statusesEntity.getExtendedEntities().getMediaEntityList();
                if (listMediaExtendedEntity != null) {
                    extendedEntities.setMediaList(getMedia(listMediaExtendedEntity));
                }
            }

            Statuses statuses = new Statuses();
            statuses.setEntities(entities);
            statuses.setExtendedEntities(extendedEntities);
            User user = new User();
            user.setProfileImage(statusesEntity.getUserEntity().getProfileImage());
            user.setScreenName(statusesEntity.getUserEntity().getScreenName());
            statuses.setUser(user);
            statusesList.add(statuses);
        }
        return statusesList;
    }

    private List<Media> getMedia(List<MediaEntity> entities) {
        List<Media> mediaList = new ArrayList<>();
        for(MediaEntity mediaEntity : entities) {
            Media media = new Media();
            media.setMediaUrlHttps(mediaEntity.getMediaUrlHttps());
            mediaList.add(media);
        }

        Timber.i("Media list :%s", mediaList.size());
        return mediaList;
    }
}
