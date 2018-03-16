package com.sergio.twitter.domain.tweets;

import com.sergio.twitter.domain.utils.Output;
import com.sergio.twitter.domain.tweets.model.SearchTweets;

public interface GetSearchTweetsOutput extends Output {

    void onSearchTweetsFetched(SearchTweets searchTweets);
}
