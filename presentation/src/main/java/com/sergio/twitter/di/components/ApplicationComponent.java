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

package com.sergio.twitter.di.components;

import android.content.Context;
import com.sergio.twitter.TwitterApplication;
import com.sergio.twitter.di.modules.ApplicationModule;
import com.sergio.twitter.di.modules.NetworkModule;
import com.sergio.twitter.di.modules.RepositoryModule;
import com.sergio.twitter.di.modules.ServicesModule;
import com.sergio.twitter.tweetdetails.TweetDetailActivity;
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

    void inject(TweetDetailActivity tweetDetailActivity);

    void inject(TwitterApplication app);

    void inject(Context context);
}
