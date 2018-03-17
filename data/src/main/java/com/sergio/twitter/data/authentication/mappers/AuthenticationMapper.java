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
