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

import com.sergio.twitter.BasePresenter;
import com.sergio.twitter.common.UserView;
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

    private String query = "#lovewhereyouwork filter:images";

    private GetSearchTweetsInteractor getSearchTweetsInteractor;
    private GetAuthenticationInteractor getAuthenticationInteractor;
    private PreferenceRepositoryImpl preferenceRepository;
    private TweetsView view;
    private String maxId;
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
        view.showLoading();
        GetSearchTweetsRequest request = new GetSearchTweetsRequest();
        request.setQueries(query);

        if (maxId != null && !newQuery) {
            request.setMaxId(maxId);
        }

        registerDisposable(getSearchTweetsInteractor.execute(request, new GetSearchTweetsOutput() {
            @Override
            public void onSearchTweetsFetched(SearchTweets searchTweets) {
                maxId = searchTweets.getSearchMetadata().getMaxId();
                view.hideLoading();
                List<UserView> content = getUser(searchTweets);
                if (newQuery) {
                    view.setContent(content);
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

    private List<UserView> getUser(SearchTweets searchTweets) {
        Timber.i(" Total tweets received: "+ searchTweets.getSearchMetadata().getCount());
        List<UserView> userViewList = new ArrayList<>();
        for (Statuses statuses: searchTweets.getStatuses()) {
            UserView userView = new UserView();
            List<Media> mediaList = statuses.getEntities().getMediaList();
            if (mediaList != null) {
                for(Media media: mediaList) {
                    Timber.i(" Media url founded: "+ media.getMediaUrlHttps());
                    mediaList.add(media);
                }

                userView.setUserName(statuses.getUser().getScreenName());
                userView.setMediaList(mediaList);
                userView.setProfileImage(statuses.getUser().getProfileImage());
                userViewList.add(userView);
            }
        }

        return userViewList;
    }

    @Override
    public void destroy() {
        clearSubscriptions();
    }

    public void onRefreshLayout() {
        newQuery = true;
        loadData();
    }

    public void onScrollFinish() {
        newQuery = false;
        loadData();
    }

    public void onQueryTextSubmit(String query) {
        this.query = query;
        newQuery = true;
        loadData();
    }
}
