package com.sergio.twitter.data.authentication.network;

import com.sergio.twitter.data.authentication.model.AuthenticationEntity;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthenticationService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    Single<AuthenticationEntity> getAuthentication(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType);

}
