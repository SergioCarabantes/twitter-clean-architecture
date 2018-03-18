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

import com.sergio.twitter.data.authentication.AuthenticationRepositoryImpl;
import com.sergio.twitter.data.preferences.PreferenceRepositoryImpl;
import com.sergio.twitter.data.tweets.SearchTweetsRepositoryImpl;
import com.sergio.twitter.domain.authentication.AuthenticationRepository;
import com.sergio.twitter.domain.preferences.PreferenceRepository;
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
