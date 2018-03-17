package com.sergio.twitter.domain.authentication;

import com.sergio.twitter.domain.utils.Output;

public interface GetAuthenticationOutput extends Output {

    void onAuthenticationSuccess();
}
