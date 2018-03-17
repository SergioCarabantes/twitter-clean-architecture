package com.sergio.twitter.tweets.presenter;

import com.sergio.twitter.BasePresenter;
import com.sergio.twitter.data.local.PreferenceRepositoryImpl;
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

    private GetSearchTweetsInteractor getSearchTweetsInteractor;
    private GetAuthenticationInteractor getAuthenticationInteractor;
    private PreferenceRepositoryImpl preferenceRepository;
    private TweetsView view;

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
        registerDisposable(getSearchTweetsInteractor.execute(
                new GetSearchTweetsRequest("#lovewhereyouwork filter:images"), new GetSearchTweetsOutput() {
            @Override
            public void onSearchTweetsFetched(SearchTweets searchTweets) {
                view.hideLoading();
                view.showContent(getContent(searchTweets));
            }

            @Override
            public void onUnknownError(Throwable throwable) {
                //no-op
            }
        }));
    }

    private List<Media> getContent(SearchTweets searchTweets) {
        List<Media> mediaList = new ArrayList<>();
        for (Statuses statuses: searchTweets.getStatuses()) {
            List<Media> temp = statuses.getEntities().getMediaList();
            if (temp != null) {
                for(Media media: temp) {
                    Timber.i("YEAH!!!: "+ media.getMediaUrlHttps());
                    mediaList.add(media);
                }
            }
        }

        return mediaList;
    }

    @Override
    public void destroy() {
        clearSubscriptions();
    }
}
