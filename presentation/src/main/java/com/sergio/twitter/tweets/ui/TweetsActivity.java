package com.sergio.twitter.tweets.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.sergio.twitter.BaseActivity;
import com.sergio.twitter.R;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.di.components.ApplicationComponent;
import com.sergio.twitter.domain.tweets.model.Media;
import com.sergio.twitter.tweets.presenter.TweetsPresenter;
import com.sergio.twitter.tweets.view.TweetsView;

import javax.inject.Inject;
import java.util.List;

public class TweetsActivity extends BaseActivity implements TweetsView {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Inject TweetsPresenter presenter;
    @Inject TweetsGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        initializeRecyclerView();
        disableIconInToolbar();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_tweets;
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    private void initializeInjector() {
        ApplicationComponent applicationComponent =
                ((TwitterApplication) getApplication()).getApplicationComponent();
        applicationComponent.inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showContent(List<Media> content) {
        adapter.setContent(content);
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

}
