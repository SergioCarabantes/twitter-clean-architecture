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

package com.sergio.twitter.domain.tweets;

import com.sergio.twitter.domain.utils.Request;

public class GetSearchTweetsRequest implements Request {

    private String queries;

    private int count;

    private String resultType;

    private String includeEntities;

    public String getMaxId() {
        return maxId;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }

    private String maxId;

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

    public String getQueries() {
        return queries;
    }

    public void setQueries(String queries) {
        this.queries = queries;
    }
}
