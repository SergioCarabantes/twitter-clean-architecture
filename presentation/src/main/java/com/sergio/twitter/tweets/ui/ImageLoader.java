package com.sergio.twitter.tweets.ui;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

public class ImageLoader {

    private final RequestManager requestManager;

    @Inject
    public ImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    public void loadImage(String url, ImageView imageView) {
        requestManager.load(url).into(imageView);
    }

}
