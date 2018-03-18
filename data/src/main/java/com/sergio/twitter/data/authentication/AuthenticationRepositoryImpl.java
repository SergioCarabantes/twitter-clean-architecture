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

package com.sergio.twitter.data.authentication;

import com.sergio.twitter.data.authentication.mappers.AuthenticationMapper;
import com.sergio.twitter.data.authentication.network.AuthenticationService;
import com.sergio.twitter.data.utils.EncodedCredentials;
import com.sergio.twitter.domain.authentication.AuthenticationRepository;
import com.sergio.twitter.domain.authentication.model.Authentication;
import io.reactivex.Single;

import javax.inject.Inject;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    private final AuthenticationService authenticationService;
    private final AuthenticationMapper authenticationMapper;
    private final EncodedCredentials encodedCredentials;

    @Inject
    public AuthenticationRepositoryImpl(AuthenticationService authenticationService,
                                        AuthenticationMapper authenticationMapper,
                                        EncodedCredentials encodedCredentials) {
        this.authenticationService = authenticationService;
        this.authenticationMapper = authenticationMapper;
        this.encodedCredentials = encodedCredentials;
    }

    @Override
    public Single<Authentication> getAuthentication() {
        return authenticationService.getAuthentication(encodedCredentials.getCredentials(), GRANT_TYPE_CLIENT_CREDENTIALS)
                .map(authenticationMapper::map);
    }
}
