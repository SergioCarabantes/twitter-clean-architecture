package com.sergio.twitter.domain.tweets;

import com.sergio.twitter.domain.utils.Interactor;
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
        return searchTweetsRepository.getTweetsList(request.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(output::onSearchTweetsFetched,
                        output::onUnknownError);
    }
}
