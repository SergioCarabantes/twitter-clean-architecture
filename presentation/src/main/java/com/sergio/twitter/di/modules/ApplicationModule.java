/*
 * Copyright (C) 2018 Sergio Carabantes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergio.twitter.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.tweets.ui.ImageLoader;
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
    ImageLoader provideImageLoader() {
        return new ImageLoader(application.getApplicationContext());
    }

    @Provides
    @Singleton
    public static SharedPreferences provideLocalSharedPrefs(Context context) {
        return context.getSharedPreferences(TWITTER_SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }
}
