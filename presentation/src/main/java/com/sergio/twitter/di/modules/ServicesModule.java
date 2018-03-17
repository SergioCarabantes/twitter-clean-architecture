package com.sergio.twitter.di.modules;

import com.sergio.twitter.data.authentication.network.AuthenticationService;
import com.sergio.twitter.data.tweets.network.TweetsService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Module
public final class ServicesModule {

    @Provides
    @Singleton
    public static AuthenticationService provideAuthenticationService(Retrofit retrofit) {
        return retrofit.create(AuthenticationService.class);
    }

    @Provides
    @Singleton
    public static TweetsService provideTweetsService(Retrofit retrofit) {
        return retrofit.create(TweetsService.class);
    }

}
