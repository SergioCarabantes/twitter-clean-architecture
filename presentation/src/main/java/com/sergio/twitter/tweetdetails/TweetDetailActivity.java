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

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.sergio.twitter.BaseActivity;
import com.sergio.twitter.R;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.di.components.ApplicationComponent;
import com.sergio.twitter.tweets.ui.ImageLoader;

import javax.inject.Inject;

import static com.sergio.twitter.tweets.ui.ImageLoader.*;

public class TweetDetailActivity extends BaseActivity implements ImageLoaderListener {

    public static String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static String EXTRA_SCREEN_NAME = "EXTRA_SCREEN_NAME";
    public static String EXTRA_PROFILE_PIC = "EXTRA_PROFILE_PIC";

    @BindView(R.id.container) ConstraintLayout container;
    @BindView(R.id.image_view_detail) ImageView imageBackground;
    @BindView(R.id.image_view_profile_pic) ImageView profilePicImageView;
    @BindView(R.id.text_view_screen_name) TextView screenNameTextView;
    @Inject ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        String url = getIntent().getStringExtra(EXTRA_IMAGE);
        String screenName = getIntent().getStringExtra(EXTRA_SCREEN_NAME);
        String profilePic = getIntent().getStringExtra(EXTRA_PROFILE_PIC);
        imageLoader.loadImage(url, imageBackground, this);
        imageLoader.loadImage(profilePic, profilePicImageView, this);
        screenNameTextView.setText(screenName);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_tweet_detail;
    }

    private void initializeInjector() {
        ApplicationComponent applicationComponent =
                ((TwitterApplication) getApplication()).getApplicationComponent();
        applicationComponent.inject(this);
    }

    @Override
    public void onResourceReady(Bitmap bitmap) {
        Palette.from(bitmap)
                .generate(palette -> {
                    Palette.Swatch textSwatch = palette.getDarkMutedSwatch();
                    if (textSwatch != null) {
                        container.setBackgroundColor(textSwatch.getRgb());
                    }

                });
    }
}
