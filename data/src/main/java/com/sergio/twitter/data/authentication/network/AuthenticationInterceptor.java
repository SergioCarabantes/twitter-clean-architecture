package com.sergio.twitter.data.authentication.network;

import com.sergio.twitter.domain.local.PreferenceRepository;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE = "Bearer %s";
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
                .addHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build();

        return chain.proceed(request);
    }

}
