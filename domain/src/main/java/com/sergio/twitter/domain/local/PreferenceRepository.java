package com.sergio.twitter.domain.local;

public interface PreferenceRepository {

    void saveAccessToken(String accessToken);

    String getAccessToken();

}
