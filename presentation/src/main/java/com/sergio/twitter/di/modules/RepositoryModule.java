package com.sergio.twitter.di.modules;

import com.sergio.twitter.data.authentication.AuthenticationRepositoryImpl;
import com.sergio.twitter.data.local.PreferenceRepositoryImpl;
import com.sergio.twitter.data.tweets.SearchTweetsRepositoryImpl;
import com.sergio.twitter.domain.authentication.AuthenticationRepository;
import com.sergio.twitter.domain.local.PreferenceRepository;
import com.sergio.twitter.domain.tweets.SearchTweetsRepository;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    public abstract AuthenticationRepository bindAuthenticationRepository(AuthenticationRepositoryImpl repository);

    @Binds
    public abstract SearchTweetsRepository bindSearchTweetsRepository(SearchTweetsRepositoryImpl repository);

    @Binds
    public abstract PreferenceRepository bindPreferenceRepository(PreferenceRepositoryImpl repository);

}
