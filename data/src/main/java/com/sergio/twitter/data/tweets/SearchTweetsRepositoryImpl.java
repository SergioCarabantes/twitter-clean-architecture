package com.sergio.twitter.data.tweets;

import com.sergio.twitter.data.tweets.network.TweetsService;
import com.sergio.twitter.domain.tweets.SearchTweetsRepository;
import com.sergio.twitter.domain.tweets.model.SearchTweets;
import com.sergio.twitter.data.tweets.mappers.TweetsListMapper;
import io.reactivex.Single;

import javax.inject.Inject;

public class SearchTweetsRepositoryImpl implements SearchTweetsRepository {

    private final TweetsService tweetsService;
    private final TweetsListMapper tweetsListMapper;

    @Inject
    public SearchTweetsRepositoryImpl(TweetsService tweetsService,
                                      TweetsListMapper tweetsListMapper) {
        this.tweetsService = tweetsService;
        this.tweetsListMapper = tweetsListMapper;
    }

    @Override
    public Single<SearchTweets> getTweetsList(String queries) {
        return tweetsService.getTweetList(queries)
                .map(tweetsListMapper::map);
    }

}
