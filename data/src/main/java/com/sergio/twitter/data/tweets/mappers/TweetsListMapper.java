package com.sergio.twitter.data.tweets.mappers;

import com.sergio.twitter.data.Mapper;
import com.sergio.twitter.data.tweets.model.MediaEntity;
import com.sergio.twitter.data.tweets.model.SearchTweetsEntity;
import com.sergio.twitter.data.tweets.model.StatusesEntity;
import com.sergio.twitter.domain.tweets.model.Entities;
import com.sergio.twitter.domain.tweets.model.Media;
import com.sergio.twitter.domain.tweets.model.SearchTweets;
import com.sergio.twitter.domain.tweets.model.Statuses;

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
        searchTweets.setStatuses(getStatuses(searchTweetsEntity.getGetStatusesEntity()));
        return searchTweets;
    }

    private List<Statuses> getStatuses(List<StatusesEntity> statusesEntityList) {
        List<Statuses> statusesList = new ArrayList<>();
        for(StatusesEntity statusesEntity: statusesEntityList) {
            Entities entities = new Entities();

            List<MediaEntity> listMediaEntity = statusesEntity.getEntity().getGetListMediaEntity();
            if (listMediaEntity != null) {
                entities.setMediaList(getMedia(listMediaEntity));
            }
            Statuses statuses = new Statuses();
            statuses.setEntities(entities);
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
        return mediaList;
    }
}
