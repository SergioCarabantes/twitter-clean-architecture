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

package com.sergio.twitter.domain.authentication;

import com.sergio.twitter.domain.preferences.PreferenceRepository;
import com.sergio.twitter.domain.utils.NoArgInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class GetAuthenticationInteractor implements NoArgInteractor<GetAuthenticationOutput> {

    private AuthenticationRepository authenticationRepository;
    private PreferenceRepository preferenceRepository;

    @Inject
    GetAuthenticationInteractor(AuthenticationRepository authenticationRepository, PreferenceRepository preferenceRepository) {
        this.authenticationRepository = authenticationRepository;
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public Disposable execute(GetAuthenticationOutput output) {
        return authenticationRepository.getAuthentication()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((authentication, throwable) -> {
                    if (authentication == null) {
                        output.onUnknownError(throwable);
                    } else {
                        preferenceRepository.saveAccessToken(authentication.getAccessToken());
                        output.onAuthenticationSuccess();
                    }
                });
    }
}
