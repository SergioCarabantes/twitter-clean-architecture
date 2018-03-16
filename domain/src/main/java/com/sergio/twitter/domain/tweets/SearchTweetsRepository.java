package com.sergio.twitter.domain.tweets;

import com.sergio.twitter.domain.tweets.model.SearchTweets;
import io.reactivex.Single;

public interface SearchTweetsRepository {

    Single<SearchTweets> getTweetsList(String queries);

}
