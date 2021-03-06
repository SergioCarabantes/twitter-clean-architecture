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
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.sergio.twitter.BaseActivity;
import com.sergio.twitter.R;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.di.components.ApplicationComponent;
import com.sergio.twitter.tweets.ui.ImageLoader;

import javax.inject.Inject;
import java.util.ArrayList;

import static com.sergio.twitter.tweets.ui.ImageLoader.ImageLoaderListener;

public class TweetDetailActivity extends BaseActivity implements ImageLoaderListener {

    public static String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static String EXTRA_SCREEN_NAME = "EXTRA_SCREEN_NAME";
    public static String EXTRA_PROFILE_PIC = "EXTRA_PROFILE_PIC";

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.image_view_profile_pic) ImageView profilePicImageView;
    @BindView(R.id.text_view_screen_name) TextView screenNameTextView;

    @Inject ImageLoader imageLoader;
    @Inject TweetsDetailAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        initializeRecyclerView();
        disableTitleInToolbar();
        initUI();

    }

    private void initializeRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initUI() {
        ArrayList<String> urlList = getIntent().getStringArrayListExtra(EXTRA_IMAGE);
        String screenName = getIntent().getStringExtra(EXTRA_SCREEN_NAME);
        String profilePic = getIntent().getStringExtra(EXTRA_PROFILE_PIC);
        adapter.setImageLoaderListener(this);
        adapter.setContent(urlList);
        imageLoader.loadImageCircular(profilePic, profilePicImageView);
        screenNameTextView.setText(String.format("@%s", screenName));

        if (urlList.size() > 1 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementReturnTransition(null);
        }
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
        setupBackgroundColor(bitmap);
    }

    private void setupBackgroundColor(Bitmap bitmap) {
        Palette.from(bitmap)
                .generate(palette -> {
                    Palette.Swatch textSwatch = palette.getDarkMutedSwatch();
                    if (textSwatch != null) {
                        recyclerView.setBackgroundColor(textSwatch.getRgb());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            setupStatusBarAndNavColor(textSwatch.getRgb());
                        }

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupStatusBarAndNavColor(@ColorInt int color) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
        window.setNavigationBarColor(color);
    }
}
