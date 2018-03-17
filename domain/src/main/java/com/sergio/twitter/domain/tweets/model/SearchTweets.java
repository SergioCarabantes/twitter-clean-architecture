package com.sergio.twitter.domain.tweets.model;

import java.util.List;

public class SearchTweets {

    private List<Statuses> statuses;

    private SearchMetadata searchMetadata;

    public List<Statuses> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Statuses> statuses) {
        this.statuses = statuses;
    }

    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }
}
