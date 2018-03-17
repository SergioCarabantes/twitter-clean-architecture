package com.sergio.twitter.di.components;

import android.content.Context;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.di.modules.ApplicationModule;
import com.sergio.twitter.di.modules.NetworkModule;
import com.sergio.twitter.di.modules.RepositoryModule;
import com.sergio.twitter.di.modules.ServicesModule;
import com.sergio.twitter.domain.tweets.SearchTweetsRepository;
import com.sergio.twitter.tweets.ui.ImageLoader;
import com.sergio.twitter.tweets.ui.TweetsActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                RepositoryModule.class,
                ServicesModule.class,
                NetworkModule.class})
public interface ApplicationComponent {

    void inject(TweetsActivity mainActivity);

    void inject(TwitterApplication app);

    void inject(Context context);

    SearchTweetsRepository searchTweetsRepository();

}
