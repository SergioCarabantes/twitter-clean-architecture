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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import com.sergio.twitter.BaseActivity;
import com.sergio.twitter.R;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.common.UserData;
import com.sergio.twitter.di.components.ApplicationComponent;
import com.sergio.twitter.domain.tweets.model.Media;
import com.sergio.twitter.tweetdetails.TweetDetailActivity;
import com.sergio.twitter.tweets.presenter.TweetsPresenter;
import com.sergio.twitter.tweets.view.TweetsView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.sergio.twitter.tweets.ui.TweetsGridAdapter.ImageClickListener;

public class TweetsActivity extends BaseActivity implements TweetsView, ImageClickListener {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

    @Inject TweetsPresenter presenter;
    @Inject TweetsGridAdapter adapter;

    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        initializeRecyclerView();
        initializeRefreshLayout();
        disableIconInToolbar();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_tweets;
    }


    private void initializeRecyclerView() {
        final int columns = getResources().getInteger(R.integer.grid_columns);
        gridLayoutManager = new GridLayoutManager(this, columns);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        adapter.setOnImageClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void initializeInjector() {
        ApplicationComponent applicationComponent =
                ((TwitterApplication) getApplication()).getApplicationComponent();
        applicationComponent.inject(this);
    }

    private void initializeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefreshLayout());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addContent(List<UserData> content) {
        adapter.addContent(content);
    }

    @Override
    public void setContent(List<UserData> content) {
        adapter.setContent(content);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {

        private int previousTotal = 0;
        private boolean isStillLoading = true;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = gridLayoutManager.getChildCount();
            int totalItemCount = gridLayoutManager.getItemCount();
            int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

            if (isStillLoading) {
                if (totalItemCount > previousTotal) {
                    isStillLoading = false;
                    previousTotal = totalItemCount;
                }
            }
            int visibleThreshold = 2;
            if (!isStillLoading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                presenter.onScrollFinish();
                isStillLoading = true;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        initSearchView(menu);
        return true;
    }

    private void initSearchView(Menu menu) {
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorBackground));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorBackground));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onQueryTextSubmit(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onMediaClicked(ImageView imageView, UserData userData) {
        Intent intent = new Intent(this, TweetDetailActivity.class);
        ArrayList<String> urlList = new ArrayList<>();

        if (userData.getMediaListExtended() != null && userData.getMediaListExtended().size() > 1) {
            for (Media media : userData.getMediaListExtended()) {
                urlList.add(media.getMediaUrlHttps());
            }
        } else {
            for (Media media : userData.getMediaList()) {
                urlList.add(media.getMediaUrlHttps());
            }
        }

        intent.putStringArrayListExtra(TweetDetailActivity.EXTRA_IMAGE, urlList);
        intent.putExtra(TweetDetailActivity.EXTRA_SCREEN_NAME, userData.getUserName());
        intent.putExtra(TweetDetailActivity.EXTRA_PROFILE_PIC, userData.getProfileImage());
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, imageView, "image");
        startActivity(intent, options.toBundle());

    }
}
