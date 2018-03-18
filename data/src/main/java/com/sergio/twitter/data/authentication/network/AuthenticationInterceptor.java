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

package com.sergio.twitter.data.authentication.network;

import com.sergio.twitter.domain.preferences.PreferenceRepository;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE = "Bearer %s";
    private static final String AUTHORIZATION_HEADER_CONTENT_TYPE_KEY = "Content-type";
    private static final String AUTHORIZATION_HEADER_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded;charset=UTF-8";
    private PreferenceRepository preferenceRepository;

    @Inject
    public AuthenticationInterceptor(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        request = request.newBuilder()
                .addHeader(AUTHORIZATION_HEADER_KEY,
                        String.format(AUTHORIZATION_HEADER_VALUE, preferenceRepository.getAccessToken()))
                .addHeader(AUTHORIZATION_HEADER_CONTENT_TYPE_KEY, AUTHORIZATION_HEADER_CONTENT_TYPE_VALUE)
                .build();

        return chain.proceed(request);
    }

}
