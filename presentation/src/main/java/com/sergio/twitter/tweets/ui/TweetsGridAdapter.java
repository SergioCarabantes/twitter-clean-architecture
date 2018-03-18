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

package com.sergio.twitter.tweets.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sergio.twitter.R;
import com.sergio.twitter.common.UserData;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TweetsGridAdapter extends RecyclerView.Adapter<TweetsViewHolder> {

    private List<UserData> userDataList = new ArrayList<>();
    private ImageLoader imageLoader;
    private ImageClickListener imageClickListener;

    @Inject
    public TweetsGridAdapter(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    public TweetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, parent, false);
        return new TweetsViewHolder(view, imageLoader);
    }

    @Override
    public void onBindViewHolder(TweetsViewHolder holder, int position) {
        holder.bind(userDataList.get(position), imageClickListener);
    }

    @Override
    public int getItemCount() {
        Timber.i("SIZE: " + userDataList.size());
        return userDataList.size();
    }

    public void setOnImageClickListener(ImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public void setContent(List<UserData> userDataList) {
        this.userDataList = userDataList;
        notifyDataSetChanged();
    }

    public void addContent(List<UserData> mediaList) {
        this.userDataList.addAll(mediaList);
        notifyDataSetChanged();
    }

    public interface ImageClickListener {

        void onMediaClicked(ImageView imageView, UserData userData);
    }
}
