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

package com.sergio.twitter.data.authentication.mappers;

import com.sergio.twitter.data.Mapper;
import com.sergio.twitter.data.authentication.model.AuthenticationEntity;
import com.sergio.twitter.domain.authentication.model.Authentication;

import javax.inject.Inject;

public class AuthenticationMapper implements Mapper<AuthenticationEntity, Authentication> {

    @Inject
    public AuthenticationMapper() {
    }

    @Override
    public Authentication map(AuthenticationEntity entity) {
        Authentication authentication = new Authentication();
        authentication.setTokenType(entity.getTokenType());
        authentication.setAccessToken(entity.getAccessToken());
        return authentication;
    }
}
