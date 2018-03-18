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

package com.sergio.twitter.tweetdetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sergio.twitter.R;
import com.sergio.twitter.tweets.ui.ImageLoader;
import com.sergio.twitter.tweets.ui.ImageLoader.ImageLoaderListener;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TweetsDetailAdapter extends RecyclerView.Adapter<TweetsDetailViewHolder> {

    private List<String> urlList = new ArrayList<>();
    private ImageLoader imageLoader;
    private ImageLoaderListener listener;

    @Inject
    public TweetsDetailAdapter(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setImageLoaderListener(ImageLoaderListener listener) {
        this.listener = listener;
    }

    @Override
    public TweetsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_tweet, parent, false);
        return new TweetsDetailViewHolder(view, imageLoader);
    }

    @Override
    public void onBindViewHolder(TweetsDetailViewHolder holder, int position) {
        holder.bind(urlList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        Timber.i("SIZE: " + urlList.size());
        return urlList.size();
    }

    public void setContent(List<String> userDataList) {
        this.urlList = userDataList;
        notifyDataSetChanged();
    }
}
