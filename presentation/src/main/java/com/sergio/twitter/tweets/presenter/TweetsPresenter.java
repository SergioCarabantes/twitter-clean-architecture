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

package com.sergio.twitter.tweets.presenter;

import android.support.annotation.VisibleForTesting;
import com.sergio.twitter.BasePresenter;
import com.sergio.twitter.common.UserData;
import com.sergio.twitter.data.preferences.PreferenceRepositoryImpl;
import com.sergio.twitter.domain.authentication.GetAuthenticationInteractor;
import com.sergio.twitter.domain.authentication.GetAuthenticationOutput;
import com.sergio.twitter.domain.tweets.GetSearchTweetsInteractor;
import com.sergio.twitter.domain.tweets.GetSearchTweetsOutput;
import com.sergio.twitter.domain.tweets.GetSearchTweetsRequest;
import com.sergio.twitter.domain.tweets.model.Media;
import com.sergio.twitter.domain.tweets.model.SearchTweets;
import com.sergio.twitter.domain.tweets.model.Statuses;
import com.sergio.twitter.tweets.view.TweetsView;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TweetsPresenter extends BasePresenter {

    private String query = "#lovewhereyouwork";

    private GetSearchTweetsInteractor getSearchTweetsInteractor;
    private GetAuthenticationInteractor getAuthenticationInteractor;
    private PreferenceRepositoryImpl preferenceRepository;
    private TweetsView view;
    private String nextResults;
    private boolean newQuery;

    @Inject
    public TweetsPresenter(GetSearchTweetsInteractor getSearchTweetsInteractor,
                           GetAuthenticationInteractor getAuthenticationInteractor,
                           PreferenceRepositoryImpl preferenceRepository) {
        this.getSearchTweetsInteractor = getSearchTweetsInteractor;
        this.getAuthenticationInteractor = getAuthenticationInteractor;
        this.preferenceRepository = preferenceRepository;
    }

    public void setView(TweetsView view) {
        this.view = view;
    }

    @Override
    public void resume() {
        view.showLoading();
        loadData();
    }

    private void loadData() {
        if (preferenceRepository.getAccessToken() != null) {
            loadTweets();
        } else {
            checkAuthentication();
        }
    }

    private void checkAuthentication() {
        registerDisposable(getAuthenticationInteractor.execute(new GetAuthenticationOutput() {
            @Override
            public void onAuthenticationSuccess() {
                loadTweets();
            }

            @Override
            public void onUnknownError(Throwable throwable) {
                //no-op
            }
        }));
    }

    private void loadTweets() {
        GetSearchTweetsRequest request = new GetSearchTweetsRequest();
        if (nextResults != null && !newQuery) {
            request.setNextResults(nextResults);
        } else {
            request.setQueries(query + " filter:images");
            request.setCount("50");
            request.setIncludeEntities(true);
            request.setResultType("recent");
            request.setMode("extended");
        }

        registerDisposable(getSearchTweetsInteractor.execute(request, new GetSearchTweetsOutput() {
            @Override
            public void onSearchTweetsFetched(SearchTweets searchTweets) {
                nextResults = searchTweets.getSearchMetadata().getNextResults();
                view.hideLoading();
                List<UserData> content = getUser(searchTweets);
                if (newQuery) {
                    view.setContent(content);
                    newQuery = false;
                } else {
                    view.addContent(content);
                }
            }

            @Override
            public void onUnknownError(Throwable throwable) {
                //no-op
            }
        }));
    }

    private List<UserData> getUser(SearchTweets searchTweets) {
        Timber.d(" Total tweets received: %s", searchTweets.getSearchMetadata().getCount());
        List<UserData> userDataList = new ArrayList<>();
        for (Statuses statuses: searchTweets.getStatuses()) {
            UserData userData = new UserData();
            List<Media> mediaList = statuses.getEntities().getMediaList();
            if (mediaList != null) {
                Timber.d(" Media founded: %s", mediaList.size());
                userData.setUserName(statuses.getUser().getScreenName());
                userData.setMediaList(mediaList);
                userData.setProfileImage(statuses.getUser().getProfileImage());

                if (statuses.getExtendedEntities() != null) {
                    List<Media> mediaListExtended = statuses.getExtendedEntities().getMediaList();
                    if (mediaListExtended != null) {
                        userData.setMediaListExtended(mediaListExtended);
                    }
                }

                userDataList.add(userData);
            }

        }

        return userDataList;
    }

    @Override
    public void destroy() {
        clearSubscriptions();
    }

    public void onRefreshLayout() {
        view.showLoading();
        newQuery = true;
        loadData();
    }

    public void onScrollFinish() {
        newQuery = false;
        loadData();
    }

    public void onQueryTextSubmit(String query) {
        this.query = query;
        view.showLoading();
        newQuery = true;
        loadData();
    }

    @VisibleForTesting
    public void setNewQuery(boolean newquery) {
        this.newQuery = newquery;
    }
}
