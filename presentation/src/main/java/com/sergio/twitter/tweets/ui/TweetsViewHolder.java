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

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.sergio.twitter.R;
import com.sergio.twitter.common.UserData;
import com.sergio.twitter.tweets.ui.TweetsGridAdapter.ImageClickListener;

import javax.inject.Inject;

public class TweetsViewHolder extends InjectedViewHolder {

    @BindView(R.id.image_view) ImageView imageView;
    @BindView(R.id.multiple) TextView textViewMultiple;

    ImageLoader imageLoader;
    private UserData userData;
    private ImageClickListener imageClickListener;

    @Inject
    public TweetsViewHolder(View itemView, ImageLoader imageLoader) {
        super(itemView);
        this.imageLoader = imageLoader;
    }

    public void bind(UserData userData, ImageClickListener imageClickListener) {
        this.userData = userData;
        this.imageClickListener = imageClickListener;
        imageLoader.loadImage(userData.getMediaList().get(0).getMediaUrlHttps(), imageView, null);
        if (userData.getMediaListExtended().size() > 1) {
            textViewMultiple.setVisibility(View.VISIBLE);
        } else {
            textViewMultiple.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.item_container)
    public void onClickRow() {
        imageClickListener.onMediaClicked(imageView, userData);

    }
}
