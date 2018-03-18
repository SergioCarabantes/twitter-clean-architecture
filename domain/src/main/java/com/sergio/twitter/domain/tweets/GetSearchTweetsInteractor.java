package com.sergio.twitter.domain.tweets;

import com.sergio.twitter.domain.utils.Interactor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class GetSearchTweetsInteractor implements Interactor<GetSearchTweetsRequest, GetSearchTweetsOutput> {

    private SearchTweetsRepository searchTweetsRepository;

    @Inject
    GetSearchTweetsInteractor(SearchTweetsRepository searchTweetsRepository) {
        this.searchTweetsRepository = searchTweetsRepository;
    }

    @Override
    public Disposable execute(GetSearchTweetsRequest request, GetSearchTweetsOutput output) {
        if (request.getMaxId() != null) {
            return searchTweetsRepository.getNextTweetsList(request.getQueries(), request.getMaxId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(output::onSearchTweetsFetched,
                            output::onUnknownError);
        } else {
            return searchTweetsRepository.getTweetsList(request.getQueries())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(output::onSearchTweetsFetched,
                            output::onUnknownError);
        }

    }
}
