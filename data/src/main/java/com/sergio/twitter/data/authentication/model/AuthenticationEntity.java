package com.sergio.twitter.data.authentication.model;

import com.google.gson.annotations.SerializedName;

public class AuthenticationEntity {

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
