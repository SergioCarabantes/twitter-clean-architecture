package com.sergio.twitter.domain.tweets;

import com.sergio.twitter.domain.utils.Request;

public class GetSearchTweetsRequest implements Request {

    private String queries;
    private int count;
    private String resultType;
    private String includeEntities;

    public GetSearchTweetsRequest(String queries) {
        this.queries = queries;
    }

    public String getQueries() {
        return queries;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getIncludeEntities() {
        return includeEntities;
    }

    public void setIncludeEntities(String includeEntities) {
        this.includeEntities = includeEntities;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
