package com.sergio.twitter.tweets.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sergio.twitter.R;
import com.sergio.twitter.domain.tweets.model.Media;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TweetsGridAdapter extends RecyclerView.Adapter<TweetsViewHolder> {

    private List<Media> mediaList = new ArrayList<>();
    private ImageLoader imageLoader;

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
        holder.bind(mediaList.get(position));
    }

    @Override
    public int getItemCount() {
        Timber.i("SIZE: " + mediaList.size());
        return mediaList.size();
    }

    public void setContent(List<Media> mediaList) {
        this.mediaList = mediaList;
        notifyDataSetChanged();
    }
}
