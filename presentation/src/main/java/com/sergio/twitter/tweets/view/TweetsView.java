package com.sergio.twitter.tweets.view;

import com.sergio.twitter.BaseView;
import com.sergio.twitter.domain.tweets.model.Media;

import java.util.List;

public interface TweetsView extends BaseView {

    void addContent(List<Media> content);

    void setContent(List<Media> content);

}