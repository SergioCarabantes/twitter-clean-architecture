package com.sergio.twitter.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.sergio.twitter.TwitterApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ApplicationModule {

    private static final String TWITTER_SHARED_PREFS_NAME = "twitter_shared_prefs_name";

    private final TwitterApplication application;

    public ApplicationModule(TwitterApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public static SharedPreferences provideLocalSharedPrefs(Context context) {
        return context.getSharedPreferences(TWITTER_SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }
}
