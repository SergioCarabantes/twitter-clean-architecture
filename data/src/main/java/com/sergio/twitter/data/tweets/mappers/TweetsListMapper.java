package com.sergio.twitter.data.tweets.mappers;

import com.sergio.twitter.Mapper;
import com.sergio.twitter.data.tweets.model.SearchTweetsEntity;
import com.sergio.twitter.domain.tweets.model.SearchTweets;

import javax.inject.Inject;

public class TweetsListMapper implements Mapper<SearchTweetsEntity, SearchTweets> {

    @Inject
    public TweetsListMapper() {
    }

    @Override
    public SearchTweets map(SearchTweetsEntity searchTweetsEntity) {
        return new SearchTweets();
    }
}
