package com.sergio.twitter.tweets.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

public abstract class InjectedViewHolder extends RecyclerView.ViewHolder {

    public InjectedViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
