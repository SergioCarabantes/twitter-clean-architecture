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
import butterknife.BindView;
import butterknife.OnClick;
import com.sergio.twitter.R;
import com.sergio.twitter.common.UserView;
import com.sergio.twitter.tweets.ui.TweetsGridAdapter.ImageClickListener;

import javax.inject.Inject;

public class TweetsViewHolder extends InjectedViewHolder {

    @BindView(R.id.image_view) ImageView imageView;

    ImageLoader imageLoader;
    private UserView userView;
    private ImageClickListener imageClickListener;

    @Inject
    public TweetsViewHolder(View itemView, ImageLoader imageLoader) {
        super(itemView);
        this.imageLoader = imageLoader;
    }

    public void bind(UserView userView, ImageClickListener imageClickListener) {
        this.userView = userView;
        this.imageClickListener = imageClickListener;
        imageLoader.loadImage(userView.getMediaList().get(0).getMediaUrlHttps(), imageView, null);
    }

    @OnClick(R.id.item_container)
    public void onClickRow() {
        imageClickListener.onMediaClicked(imageView, userView);

    }
}
