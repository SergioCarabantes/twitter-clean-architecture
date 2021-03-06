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

package com.sergio.twitter.data.tweets;

import com.sergio.twitter.data.BuildConfig;
import com.sergio.twitter.data.tweets.mappers.TweetsListMapper;
import com.sergio.twitter.data.tweets.network.SearchTweetsService;
import com.sergio.twitter.domain.tweets.GetSearchTweetsRequest;
import com.sergio.twitter.domain.tweets.SearchTweetsRepository;
import com.sergio.twitter.domain.tweets.model.SearchTweets;
import io.reactivex.Single;
import timber.log.Timber;

import javax.inject.Inject;

public class SearchTweetsRepositoryImpl implements SearchTweetsRepository {

    private final SearchTweetsService searchTweetsService;
    private final TweetsListMapper tweetsListMapper;

    @Inject
    public SearchTweetsRepositoryImpl(SearchTweetsService searchTweetsService,
                                      TweetsListMapper tweetsListMapper) {
        this.searchTweetsService = searchTweetsService;
        this.tweetsListMapper = tweetsListMapper;
    }

    @Override
    public Single<SearchTweets> getTweetsList(GetSearchTweetsRequest request) {
        return searchTweetsService.getTweetList(
                request.getQueries(),
                request.getResultType(),
                request.getMaxId(),
                request.getCount(),
                request.getMode(),
                request.isIncludeEntities())
                .map(tweetsListMapper::map)
                .doOnError(Timber::e);
    }

    @Override
    public Single<SearchTweets> getNextTweetsList(GetSearchTweetsRequest request) {
        return searchTweetsService.getNextTweetList(
                BuildConfig.API_TWITTER + BuildConfig.API_TWITTER_SEARCH + request.getNextResults())
                .map(tweetsListMapper::map)
                .doOnError(Timber::e);
    }
}
