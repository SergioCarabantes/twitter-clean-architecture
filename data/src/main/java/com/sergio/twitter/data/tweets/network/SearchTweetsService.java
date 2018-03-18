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

package com.sergio.twitter.data.tweets.network;

import com.sergio.twitter.data.tweets.model.SearchTweetsEntity;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchTweetsService {

    @GET("/1.1/search/tweets.json")
    Single<SearchTweetsEntity> getTweetList(@Query("q") String queries,
                                            @Query("result_type") String resultType,
                                            @Query("max_id") String maxId,
                                            @Query("count") String count,
                                            @Query("tweet_mode") String mode,
                                            @Query("include_entities") boolean includeEntities);

    @GET("/1.1/search/tweets.json{next_results}")
    Single<SearchTweetsEntity> getNextTweetList(@Path(value = "next_results", encoded = false) String nextResults);

}
