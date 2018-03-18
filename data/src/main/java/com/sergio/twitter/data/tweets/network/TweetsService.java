package com.sergio.twitter.data.tweets.network;

import com.sergio.twitter.data.tweets.model.SearchTweetsEntity;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TweetsService {

    @GET("/1.1/search/tweets.json")
    Single<SearchTweetsEntity> getTweetList(@Query("q") String queries,
                                            @Query("result_type") String resultType,
                                            @Query("include_entities") boolean includeEntities);

    @GET("/1.1/search/tweets.json")
    Single<SearchTweetsEntity> getNextTweetList(@Query("q") String queries,
                                                @Query("max_id") String maxId,
                                                @Query("result_type") String resultType,
                                                @Query("include_entities") boolean includeEntities);

}
