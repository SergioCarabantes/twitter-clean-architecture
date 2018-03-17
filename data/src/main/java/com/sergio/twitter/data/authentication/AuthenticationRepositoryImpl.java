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
