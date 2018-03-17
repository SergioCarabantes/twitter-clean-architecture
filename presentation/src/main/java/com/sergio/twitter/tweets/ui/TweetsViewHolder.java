package com.sergio.twitter.tweets.ui;

import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.sergio.twitter.R;
import com.sergio.twitter.domain.tweets.model.Media;

import javax.inject.Inject;

public class TweetsViewHolder extends InjectedViewHolder {

    @BindView(R.id.image) ImageView imageView;

    ImageLoader imageLoader;

    @Inject
    public TweetsViewHolder(View itemView, ImageLoader imageLoader) {
        super(itemView);
        this.imageLoader = imageLoader;
    }

    public void bind(Media media) {
        imageLoader.loadImage(media.getMediaUrlHttps(), imageView);
    }
}
