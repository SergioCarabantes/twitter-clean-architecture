package com.sergio.twitter.domain.authentication;

import com.sergio.twitter.domain.authentication.model.Authentication;
import io.reactivex.Single;

public interface AuthenticationRepository {

    Single<Authentication> getAuthentication();

}
